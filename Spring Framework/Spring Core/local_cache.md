## 🔹 What is a Local Cache?

A **local cache** is a cache that lives **inside the memory (JVM heap)** of a single application instance.
Unlike a **distributed cache** (e.g., Redis, Hazelcast), a local cache is **not shared** across nodes.

* **Scope** → per JVM process (per application instance).
* **Speed** → extremely fast (since it’s in-memory, no network calls).
* **Downside** → no consistency across multiple app instances.

---

## 🔹 When to Use Local Cache?

* When your application runs as a **single instance**.
* When stale data for a short time is **acceptable**.
* For **read-heavy workloads** where you want max speed.
* For **temporary in-memory lookups** (e.g., reference data, config, session-level data).

---

## 🔹 Options for Local Cache in Java/Spring Boot

1. **ConcurrentHashMap** (DIY simple cache).
2. **Caffeine** (recommended, high-performance local cache).
3. **Ehcache** (can be local or distributed).

👉 Modern Spring Boot apps prefer **Caffeine**.

---

## 🔹 Example 1: Simple Local Cache with `ConcurrentHashMap`

```java
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleCache<K, V> {
    private final Map<K, V> store = new ConcurrentHashMap<>();

    public V get(K key) {
        return store.get(key);
    }

    public void put(K key, V value) {
        store.put(key, value);
    }

    public void evict(K key) {
        store.remove(key);
    }
}
```

Usage:

```java
SimpleCache<Long, String> cache = new SimpleCache<>();
cache.put(1L, "Laptop");
System.out.println(cache.get(1L)); // -> "Laptop"
```

👉 Pros: Super simple, no dependencies.
👉 Cons: No eviction policy (risk of memory bloat).

---

## 🔹 Example 2: Local Cache with **Caffeine + Spring Boot**

Caffeine is a **high-performance caching library** developed by Google, better than Guava cache.

### 1. Add Dependency

```xml
<dependency>
    <groupId>com.github.ben-manes.caffeine</groupId>
    <artifactId>caffeine</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```

---

### 2. Configure in `application.yml`

```yaml
spring:
  cache:
    type: caffeine
    cache-names: productCache
    caffeine:
      spec: maximumSize=1000,expireAfterWrite=10m
```

* `maximumSize=1000` → store up to 1000 entries.
* `expireAfterWrite=10m` → entries auto-expire after 10 minutes.

---

### 3. Enable Caching

```java
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
}
```

---

### 4. Use in Service

```java
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Cacheable(value = "productCache", key = "#id")
    public Product getProductById(Long id) {
        simulateSlowDbCall();
        return new Product(id, "Laptop " + id);
    }

    @CacheEvict(value = "productCache", key = "#product.id")
    public void updateProduct(Product product) {
        // update DB logic
        System.out.println("Updated DB: " + product);
    }

    private void simulateSlowDbCall() {
        try { Thread.sleep(3000L); } catch (InterruptedException ignored) {}
    }
}
```

👉 Behavior:

* First call `getProductById(1)` → slow DB hit + cache it.
* Next calls (within 10 min) → super fast (served from memory).
* `updateProduct(product)` → evicts from cache, so next call fetches fresh data.

---

## 🔹 Local Cache in Multi-Instance Setup

```
         +---------------------+
         |   Client Request    |
         +----------+----------+
                    |
        ----------------------------
        |             |            |
+-------+-------+ +---+-------+ +--+--------+
| Spring App #1 | | Spring App #2 | | Spring App #3 |
|  (Local Cache)| |  (Local Cache)| |  (Local Cache)|
+---------------+ +---------------+ +---------------+
```

👉 Each app instance has its **own cache** → no sync between them.
If instance #1 updates a value, instance #2 still has stale data.

---

## 🔹 Pros & Cons of Local Cache

✅ **Pros**

* Very fast (in-memory, no network latency).
* Easy to implement.
* Great for single-instance apps.

❌ **Cons**

* No global consistency across nodes.
* Memory limited by JVM heap size.
* Risk of **OutOfMemoryError** if misconfigured.

---

## 🔹 Key Takeaway

* Use **local cache (Caffeine/Ehcache)** when:

  * Your app is single-instance, or
  * Slightly stale data is okay.

* Use **distributed cache (Redis/Hazelcast)** when:

  * You need consistency across multiple instances.
  * Your app runs in a cluster/microservices environment.

---

