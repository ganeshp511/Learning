## 🔹 What is a Global (Distributed) Cache?

A **distributed (global) cache** is a cache that is shared across multiple instances of an application (or even across multiple applications/services).

This is different from a **local (in-memory) cache** like `ConcurrentHashMap` or `Caffeine`, where the cache lives **only inside the JVM process** of a single application instance.

In microservices or scaled-out systems (multiple app servers, containers, or nodes), a **global cache** ensures:

* **Consistency**: All nodes see the same cached data.
* **Scalability**: Multiple nodes can share the load.
* **Fault Tolerance**: If one node goes down, the cache is not lost.

Common tools for distributed cache:

* **Redis** (most popular)
* **Hazelcast**
* **Apache Ignite**
* **Memcached**
* **Ehcache with Terracotta (clustered mode)**

---

## 🔹 Why Not Just Local Cache?

Imagine you have 3 instances of a Spring Boot service behind a load balancer:

* If each uses a local `@Cacheable`, they each hold their own cache copy.
* If one instance updates the data, the others may serve **stale data**.
* A distributed cache solves this by **centralizing caching in one shared store**.

---

## 🔹 Example with **Spring Boot + Redis (Global Cache)**

Redis is the most common choice for distributed caching in Spring Boot apps.

### 1. Add Dependencies

In `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```

---

### 2. Configure Redis in `application.yml`

```yaml
spring:
  cache:
    type: redis
  data:
    redis:
      host: localhost
      port: 6379
```

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

### 4. Use Caching in a Service

```java
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    // Expensive operation (e.g., DB call or API call)
    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        simulateSlowService();
        return new Product(id, "Laptop " + id);
    }

    // Remove from cache when product is updated
    @CacheEvict(value = "products", key = "#product.id")
    public void updateProduct(Product product) {
        // update DB
        System.out.println("Product updated in DB: " + product);
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
```

👉 Now:

* The first time `getProductById(1)` is called → data fetched & cached in **Redis**.
* Next calls from *any* application instance → fetched instantly from Redis.
* If `updateProduct()` is called → cache is evicted in Redis, so all app nodes see fresh data.

---

## 🔹 Multi-Node Architecture (Global Cache in Action)

```
             +---------------------+
             |  Client / Browser   |
             +----------+----------+
                        |
                +-------+-------+
                | Load Balancer |
                +-------+-------+
                        |
      ----------------------------------------
      |                  |                   |
+-----+-----+      +-----+-----+       +-----+-----+
| Spring App|      | Spring App|       | Spring App|
| Instance1 |      | Instance2 |       | Instance3 |
+-----+-----+      +-----+-----+       +-----+-----+
      |                  |                   |
      ----------------------------------------
                        |
                  +-----+-----+
                  |  Redis     |
                  | (Global    |
                  |   Cache)   |
                  +-----------+
```

---

## 🔹 Benefits

* **Consistency**: All nodes share same cache.
* **Scalability**: Add/remove app instances without cache duplication.
* **Performance**: Faster than hitting DB for every request.
* **Fault tolerance**: Cache survives even if app node crashes.

---

## 🔹 Challenges

* **Network Latency**: Remote call to Redis vs. local memory.
* **Cache Eviction Policies**: Must tune (LRU, TTL).
* **Single Point of Failure**: Redis needs clustering/replication for HA.

---

✅ In practice:

* Use **local cache + distributed cache hybrid** (e.g., Caffeine + Redis) → read from local, fallback to Redis.
* Use **Redis Cluster** for high availability.

---
