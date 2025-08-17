# Why hybrid?

* **L1 (local, e.g., Caffeine)** → ultra-fast, in-JVM hits; protects Redis/DB; per-instance.
* **L2 (global, e.g., Redis)** → shared truth across instances; survives restarts; enables cross-node coherence.
* **Goal** → hit L1 most of the time, fall back to L2, and keep L1 caches **coherent across nodes** using pub/sub invalidations.

---

# Architecture (cache-aside, 2-tier)

1. Read path: `L1.get` → miss → `L2.get` → hit ⇒ **populate L1** → return; miss ⇒ **load DB**, then **put L1 + L2**.
2. Write/Update: write DB → **evict L2** (or update) → **publish invalidation** → all nodes **evict L1** for that key.
3. TTLs: short TTL on L1, longer TTL on L2. Add **jitter** to avoid thundering expirations.
4. Stampede control: `@Cacheable(sync = true)` (per-JVM) + optional **distributed lock** for rare hot keys.

---

# Dependencies (Maven)

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
  </dependency>
  <dependency>
    <groupId>com.github.ben-manes.caffeine</groupId>
    <artifactId>caffeine</artifactId>
  </dependency>
  <!-- Optional: for JSON values in Redis -->
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
  </dependency>
</dependencies>
```

---

# Config (`application.yml`)

```yaml
spring:
  cache:
    # We'll register a custom CacheManager that wraps both Caffeine (L1) and Redis (L2).
    cache-names: products
  data:
    redis:
      host: localhost
      port: 6379

app:
  cache:
    l1:
      spec: "maximumSize=20_000,expireAfterWrite=60s"   # fast but short
    l2:
      ttl-seconds: 1800                                # longer TTL in Redis (~30m)
      jitter-seconds: 120                              # add 0..120s random jitter
    pubsub:
      topic: "cache:invalidate"
```

---

# Core idea: a **TwoLevelCache** that implements Spring’s `Cache`

Spring’s built-in `CompositeCacheManager` won’t write/populate both levels. So we implement a small adapter that:

* checks L1, falls back to L2, **warms L1** on L2 hits,
* writes/evicts **both** levels,
* listens to Redis **pub/sub** to invalidate L1 across the cluster.

### 1) Cache configuration

```java
// CacheConfig.java
package com.example.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.cache.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheConfig {

  @Value("${app.cache.l1.spec}") String caffeineSpec;
  @Value("${app.cache.l2.ttl-seconds}") long l2TtlSeconds;
  @Value("${app.cache.l2.jitter-seconds:0}") long jitterSeconds;
  @Value("${app.cache.pubsub.topic}") String topic;

  @Bean
  public org.springframework.cache.caffeine.CaffeineCacheManager caffeineCacheManager() {
    var mgr = new org.springframework.cache.caffeine.CaffeineCacheManager();
    mgr.setCaffeine(Caffeine.from(caffeineSpec));
    return mgr;
  }

  @Bean
  public RedisCacheManager redisCacheManager(RedisConnectionFactory cf) {
    RedisCacheConfiguration base = RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofSeconds(l2TtlSeconds)) // base TTL
        .disableCachingNullValues();
    return RedisCacheManager.builder(cf)
        .cacheDefaults(base)
        .build();
  }

  @Bean
  public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory cf) {
    return new StringRedisTemplate(cf);
  }

  @Bean
  public CacheInvalidationPublisher cacheInvalidationPublisher(StringRedisTemplate template) {
    return new CacheInvalidationPublisher(template, topic);
  }

  @Bean
  public CacheInvalidationSubscriber cacheInvalidationSubscriber(
      RedisConnectionFactory cf,
      TwoLevelCacheRegistry registry) {
    return new CacheInvalidationSubscriber(cf, topic, registry);
  }

  @Bean
  public TwoLevelCacheRegistry twoLevelCacheRegistry() {
    return new TwoLevelCacheRegistry();
  }

  /** This is the CacheManager Spring will use for @Cacheable, etc. */
  @Primary
  @Bean
  public CacheManager twoLevelCacheManager(
      org.springframework.cache.caffeine.CaffeineCacheManager l1,
      RedisCacheManager l2,
      CacheInvalidationPublisher publisher,
      TwoLevelCacheRegistry registry) {
    return new TwoLevelCacheManager(l1, l2, publisher, registry, jitterSeconds);
  }
}
```

### 2) The TwoLevelCacheManager + TwoLevelCache

```java
// TwoLevelCacheManager.java
package com.example.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class TwoLevelCacheManager implements CacheManager {

  private final CaffeineCacheManager l1;
  private final RedisCacheManager l2;
  private final CacheInvalidationPublisher publisher;
  private final TwoLevelCacheRegistry registry;
  private final long jitterSeconds;
  private final ConcurrentHashMap<String, Cache> caches = new ConcurrentHashMap<>();

  public TwoLevelCacheManager(CaffeineCacheManager l1,
                              RedisCacheManager l2,
                              CacheInvalidationPublisher publisher,
                              TwoLevelCacheRegistry registry,
                              long jitterSeconds) {
    this.l1 = l1; this.l2 = l2; this.publisher = publisher;
    this.registry = registry; this.jitterSeconds = jitterSeconds;
  }

  @Override public Cache getCache(String name) {
    return caches.computeIfAbsent(name, n -> {
      Cache l1Cache = l1.getCache(n);
      Cache l2Cache = l2.getCache(n);
      TwoLevelCache c = new TwoLevelCache(n, l1Cache, l2Cache, publisher, jitterSeconds);
      registry.register(c);
      return c;
    });
  }

  @Override public Collection<String> getCacheNames() {
    return l2.getCacheNames(); // use Redis-defined names
  }
}
```

```java
// TwoLevelCache.java
package com.example.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class TwoLevelCache implements Cache {

  private final String name;
  private final Cache l1, l2;
  private final CacheInvalidationPublisher publisher;
  private final long jitterSeconds;

  public TwoLevelCache(String name, Cache l1, Cache l2,
                       CacheInvalidationPublisher publisher, long jitterSeconds) {
    this.name = name; this.l1 = l1; this.l2 = l2;
    this.publisher = publisher; this.jitterSeconds = jitterSeconds;
  }

  @Override public String getName() { return name; }
  @Override public Object getNativeCache() { return this; }

  @Override public ValueWrapper get(Object key) {
    ValueWrapper v = l1.get(key);
    if (v != null) return v;
    v = l2.get(key);
    if (v != null) {
      // warm L1 from L2
      l1.put(key, v.get());
    }
    return v;
  }

  @Override @SuppressWarnings("unchecked")
  public <T> T get(Object key, Class<T> type) {
    ValueWrapper v = get(key);
    return v == null ? null : (T) v.get();
  }

  @Override public void put(Object key, Object value) {
    l2.put(key, value);         // write L2 first
    l1.put(key, value);         // then warm L1
    // publish "update" so other nodes evict L1 (or choose to warm if you carry value)
    publisher.publish(name, key);
  }

  @Override public ValueWrapper putIfAbsent(Object key, Object value) {
    if (get(key) != null) return new SimpleValueWrapper(get(key).get());
    put(key, value);
    return null;
  }

  @Override public void evict(Object key) {
    l2.evict(key);
    l1.evict(key);
    publisher.publish(name, key);
  }

  @Override public void clear() {
    l2.clear();
    l1.clear();
    publisher.publish(name, "*"); // signal "clear all" for this cache
  }

  // Optionally: per-put TTL jitter (if your L2 manager supports per-put TTL).
  private Duration withJitter(Duration base) {
    if (jitterSeconds <= 0) return base;
    long extra = ThreadLocalRandom.current().nextLong(jitterSeconds + 1);
    return base.plusSeconds(extra);
  }
}
```

### 3) Pub/Sub invalidation wiring

```java
// CacheInvalidationPublisher.java
package com.example.cache;

import org.springframework.data.redis.core.StringRedisTemplate;

public class CacheInvalidationPublisher {
  private final StringRedisTemplate template;
  private final String topic;

  public CacheInvalidationPublisher(StringRedisTemplate template, String topic) {
    this.template = template; this.topic = topic;
  }

  public void publish(String cacheName, Object key) {
    // simple "cacheName|key" message; key "*" means clear-all
    template.convertAndSend(topic, cacheName + "|" + String.valueOf(key));
  }
}
```

```java
// CacheInvalidationSubscriber.java
package com.example.cache;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;

public class CacheInvalidationSubscriber implements MessageListener {

  private final TwoLevelCacheRegistry registry;

  public CacheInvalidationSubscriber(RedisConnectionFactory cf, String topic, TwoLevelCacheRegistry registry) {
    this.registry = registry;
    var container = new RedisMessageListenerContainer();
    container.setConnectionFactory(cf);
    container.addMessageListener(this, new PatternTopic(topic));
    container.afterPropertiesSet();
    container.start();
  }

  @Override
  public void onMessage(Message message, byte[] pattern) {
    String payload = new String(message.getBody());
    int idx = payload.indexOf('|');
    if (idx <= 0) return;
    String cacheName = payload.substring(0, idx);
    String key = payload.substring(idx + 1);
    registry.invalidateLocal(cacheName, "*".equals(key) ? null : key);
  }
}
```

```java
// TwoLevelCacheRegistry.java
package com.example.cache;

import org.springframework.cache.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TwoLevelCacheRegistry {
  private final Map<String, TwoLevelCache> caches = new ConcurrentHashMap<>();
  void register(TwoLevelCache cache) { caches.put(cache.getName(), cache); }

  void invalidateLocal(String cacheName, Object key) {
    TwoLevelCache c = caches.get(cacheName);
    if (c == null) return;
    if (key == null) c.getNativeL1().clear(); // clear-all
    else c.getNativeL1().evict(key);
  }
}

// Add helper in TwoLevelCache:
  Cache getNativeL1() { return l1; }
```

*(If you prefer less custom code, see the **Redisson near cache** option at the end.)*

---

# Using it in a service

```java
// ProductService.java
package com.example.product;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  // sync=true prevents multiple threads in the SAME JVM from dogpiling this key
  @Cacheable(value = "products", key = "#id", sync = true)
  public Product getProductById(Long id) {
    simulateSlowDb();
    return new Product(id, "Laptop " + id);
  }

  @CacheEvict(value = "products", key = "#p.id") // publish invalidation → all nodes evict L1
  public void updateProduct(Product p) {
    // 1) update DB
    // 2) Optionally also evict/put in Redis if you want write-through semantics
  }

  private void simulateSlowDb() {
    try { Thread.sleep(1200); } catch (InterruptedException ignored) {}
  }
}
```

**Read path flow now:**
App A calls `getProductById(42)` → L1 miss → L2 hit → warm L1 on A.
App B also calls → L1 miss → L2 hit → warm L1 on B.
An update happens → `@CacheEvict` publishes → all nodes evict **their L1** immediately; next read warms again from L2.

---

# Production considerations & best practices

* **TTL strategy**

  * L1: very short (30–120s).
  * L2: longer (15–60m).
  * **Jitter** L2 TTL (± a few minutes) to avoid herd effects.
* **Size limits**

  * Keep L1 bounded (`maximumSize`) to avoid GC & OOM; pin only hot keys.
* **Serialization**

  * Use `GenericJackson2JsonRedisSerializer` for forward/backward compatibility.
* **Hot keys**

  * Add per-key circuit breakers; consider request coalescing at L2 via Redisson lock for ultra-hot keys.
* **Cache penetration**

  * Cache negatives briefly (e.g., “not found” → 30–60s) to avoid hammering DB for non-existent IDs.
* **Metrics & tracing**

  * Micrometer exposes `cache.*` metrics; alert on L2 hit ratio, Redis latency, and pub/sub failures.
* **Failure modes**

  * If Redis is down: continue serving from L1 (stale but available); degrade gracefully; protect DB by shedding traffic or reducing TTLs.
* **Consistency**

  * This is **eventual** across instances (tiny window). If you need read-your-writes strictly, route those reads to DB or bypass cache for a short window.

---

# Drop-in alternative: **Redisson “near cache”** (less custom code)

Redisson gives you a Redis-backed map with a **local near cache** + **invalidation events** out of the box.

```xml
<!-- Add Redisson -->
<dependency>
  <groupId>org.redisson</groupId>
  <artifactId>redisson-spring-boot-starter</artifactId>
  <version>3.27.2</version>
</dependency>
```

```java
// RedissonConfig.java
@Configuration
public class RedissonConfig {
  @Bean
  public RedissonClient redisson() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    return Redisson.create(config);
  }

  @Bean
  public RLocalCachedMap<String, Product> productsNearCache(RedissonClient client) {
    var opts = LocalCachedMapOptions.<String, Product>defaults()
        .evictionPolicy(LocalCachedMapOptions.EvictionPolicy.LRU)
        .cacheSize(20_000)
        .timeToLive(60_000) // L1 TTL ms
        .maxIdle(60_000)
        .invalidateEntryOnChange(true); // <— key bit: pub/sub invalidation
    return client.getLocalCachedMap("products", opts);
  }
}
```

You can then wrap `RLocalCachedMap` with Spring Cache or use it directly. Redisson handles **pub/sub invalidation** for you.

---

# Quick checklist

* [x] L1 Caffeine (bounded, short TTL)
* [x] L2 Redis (longer TTL, jitter)
* [x] Cache-aside pattern with `@Cacheable(sync=true)`
* [x] Cross-node L1 invalidation via Redis pub/sub
* [x] Graceful degradation & metrics

---

