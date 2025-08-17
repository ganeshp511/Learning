# 🔹 What is H2 Database?

* **H2** is an **in-memory relational database** (Java-based).
* Runs inside the same JVM as your tests → super fast.
* Schema and data disappear once the JVM shuts down.
* Supports **JDBC & SQL**, often compatible with PostgreSQL, MySQL (but not 100%).

---

# 🔹 H2 in **Unit Tests**

👉 Strictly speaking, **unit tests** should not use H2.

* A *unit test* tests a **single class in isolation** (e.g., a service).
* If the service depends on a repository, you should **mock the repository** (using Mockito).
* That way, the test doesn’t care about DB.

✅ Example (unit test with mocking, no H2 involved):

```java
@ExtendWith(MockitoExtension.class)
class ProductServiceUnitTest {

    @Mock ProductRepository repo;
    @InjectMocks ProductService service;

    @Test
    void testGetProduct() {
        when(repo.findById(1L)).thenReturn(Optional.of(new Product(1L, "Laptop")));

        Product product = service.getProduct(1L);

        assertEquals("Laptop", product.getName());
    }
}
```

⚠️ If you use H2 in a so-called “unit test”, it’s **not a unit test anymore** → it becomes an **integration test** (because it integrates with the DB layer).

---

# 🔹 H2 in **Integration Tests**

This is where H2 shines ⭐.

When testing **repositories, services, or controllers with real DB interaction**, you need a database.

* Instead of connecting to your production DB (PostgreSQL, MySQL, Oracle), you can use **H2** as a lightweight replacement.
* Spring Boot automatically wires `@DataJpaTest` or `@SpringBootTest` to use H2 if it’s on the classpath.

✅ Example: `@DataJpaTest` with H2 (testing repository)

```java
@DataJpaTest
class ProductRepositoryIT {

    @Autowired ProductRepository repo;

    @Test
    void testSaveAndFind() {
        repo.save(new Product(null, "Phone"));

        Optional<Product> found = repo.findByName("Phone");

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Phone");
    }
}
```

* This runs against **H2 in-memory DB**.
* The schema is auto-created from JPA `@Entity` mappings.
* Tests are isolated, fast, and don’t affect real data.

---

# 🔹 Why Use H2 for Integration Tests?

1. ✅ **Fast setup** (in-memory, no Docker, no external DB).
2. ✅ **Isolated** (each test run starts with a clean DB).
3. ✅ **Automatic with Spring Boot** (no extra config if dependency is present).

---

# 🔹 Limitations of H2

* Not 100% SQL-compatible with all real databases.

  * Example: PostgreSQL JSONB columns or MySQL-specific syntax won’t work.
* Risk of **false positives**: test passes on H2, but fails in prod DB.
* For production-like testing, prefer **Testcontainers** (real DB in Docker).

---

# 🔹 So, is H2 used in **Unit Tests or Integration Tests?**

* **Unit tests** ❌ → should not use H2 (use mocks).
* **Integration tests** ✅ → H2 is a popular choice for lightweight DB integration.

---

✅ **Summary**:

* H2 is an **in-memory DB**.
* Best for **integration tests** (repository, service, controller with DB).
* Not for pure **unit tests** (where mocks are used).
* For production-like behavior, **Testcontainers** (real DB) is even better.

---

