Perfect! 🚀 Now we’re getting into **Performance & Scalability** — critical for REST APIs, especially when serving many users or large datasets. Let’s go step by step.

---

# 🔹 Performance & Scalability in REST APIs

---

## 1. **Caching**

Caching = storing frequently accessed data in **memory or fast storage** to reduce DB/API calls.

* Improves **response time**
* Reduces **load on backend**

### Types of caching:

1. **In-memory caching** (Spring Cache, EhCache, Caffeine)
2. **Distributed caching** (Redis, Memcached) → for multiple instances of your service

---

### Example: Spring Cache with Redis

#### Dependencies

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

#### Enable Caching

```java
@SpringBootApplication
@EnableCaching
public class MyApplication {}
```

#### Cache Service

```java
@Service
public class CustomerService {

    @Cacheable(value = "customers", key = "#id")
    public Customer getCustomer(Long id) {
        simulateSlowService(); // pretend this is slow DB call
        return repository.findById(id).orElse(null);
    }

    private void simulateSlowService() {
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }
}
```

✅ First request → slow
✅ Subsequent requests → **served from cache**

---

## 2. **Async APIs**

Some requests are **long-running** (report generation, CSV processing, image processing).

### Approaches:

1. **Spring `@Async`**

   * Runs method in a separate thread
   * Returns `CompletableFuture` or `void`

```java
@Async
public CompletableFuture<String> processLargeFile(MultipartFile file) {
    // long-running processing
    return CompletableFuture.completedFuture("Done");
}
```

2. **Reactive APIs (Spring WebFlux)**

   * Non-blocking, reactive streams
   * Great for **high-concurrency** APIs

3. **Message Queues (RabbitMQ/Kafka)**

   * Decouple request from processing
   * Return **202 Accepted** immediately, process asynchronously

---

## 3. **Rate Limiting & Throttling**

Rate limiting = controlling **how many requests a client can make** in a period.

* Prevents abuse / DDoS
* Protects DB and backend services

### Approaches:

1. **Spring Bucket4j** (in-memory or Redis-based)
2. **API Gateway rate limiting** (Spring Cloud Gateway, Kong, NGINX)

#### Example: Bucket4j + Spring Boot

```java
@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Bucket bucket;

    public RateLimitFilter() {
        Refill refill = Refill.greedy(5, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(5, refill);
        this.bucket = Bucket4j.builder().addLimit(limit).build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (bucket.tryConsume(1)) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(429);
            response.getWriter().write("Too many requests");
        }
    }
}
```

✅ Allows **5 requests per minute** per instance

---

## 4. **Other Performance & Scalability Tips**

* **Pagination & Filtering** → never return huge datasets at once
* **Compression** → enable `GZIP` responses
* **Connection Pooling** → HikariCP for DB, keep connections reusable
* **Asynchronous I/O** → for high concurrency, use WebFlux or async controllers
* **Circuit Breakers / Resilience** → with **Resilience4j** to prevent cascading failures
* **Horizontal Scaling** → run multiple instances behind **load balancer**

---

# ✅ Summary

| Feature              | Benefit                                |
| -------------------- | -------------------------------------- |
| Caching              | Faster responses, lower DB load        |
| Async APIs           | Handles long-running tasks efficiently |
| Rate Limiting        | Prevent abuse & overload               |
| Pagination/Filtering | Avoid huge responses                   |
| Compression          | Reduce bandwidth                       |
| Circuit Breakers     | Prevent cascading failures             |

---