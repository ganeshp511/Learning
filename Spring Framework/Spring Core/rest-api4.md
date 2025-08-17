Perfect 🚀 — now we’re stepping into one of the **most critical parts** of REST APIs: **Security**.
Even the cleanest API design is useless if it’s not secure. Let’s carefully break this down.

---

# 6. Security in REST APIs

---

## 6.1 Authentication

👉 **Authentication** = proving **who you are**.
It answers the question: *“Is this request really from Aisha, or is it from someone pretending to be her?”*

There are multiple strategies:

---

### 6.1.1 Basic Authentication

* Oldest, simplest method.
* Client sends **username + password** with every request, encoded in Base64.
* Uses `Authorization` header.

Example request:

```
GET /customers
Authorization: Basic YWlzaGE6c2VjcmV0
```

(where `YWlzaGE6c2VjcmV0` = Base64("aisha\:secret"))

⚠️ Problem: insecure unless used with **HTTPS**, since credentials are exposed on every request.
👉 Usually not recommended for production, but sometimes used for **internal APIs**.

---

### 6.1.2 JWT (JSON Web Tokens)

* Modern, **stateless authentication**.
* User logs in with credentials → server generates a **JWT token**.
* Token is signed with secret/private key → prevents tampering.
* Client sends token in every request (`Authorization: Bearer <token>`).
* Server validates token **without needing DB lookup** → fast and scalable.

#### Example JWT (decoded):

```json
{
  "sub": "aisha",
  "roles": ["ROLE_USER"],
  "iat": 1692294000,
  "exp": 1692297600
}
```

* `sub` = subject (username)
* `roles` = roles/permissions
* `iat` = issued at
* `exp` = expiration time

⚠️ JWT should always have a **short lifespan** (e.g., 15 min) + use **refresh tokens**.

---

### 6.1.3 OAuth2 / OpenID Connect

* Used for **third-party login and delegated access**.
* Example: *“Login with Google / GitHub”*.
* Instead of sharing your password with every app, you authorize apps via **tokens**.

👉 OAuth2 handles **delegated authorization** (e.g., "this app can access your calendar").
👉 OpenID Connect adds **authentication** (e.g., "this is really Aisha").

Use cases:

* Public APIs (Google, Facebook, GitHub).
* Enterprise apps (SSO — Single Sign-On).

---

## 6.2 Authorization

👉 **Authorization** = defining **what you are allowed to do** after authentication.
It answers: *“Aisha is authenticated, but can she delete customers?”*

Two common strategies:

### 6.2.1 RBAC (Role-Based Access Control)

* Users are assigned **roles** (e.g., ADMIN, USER).
* Roles define what actions are allowed.

Example:

* `ADMIN` → can `GET/POST/DELETE /customers`.
* `USER` → can only `GET /customers`.

### 6.2.2 Permission-Based (Fine-grained)

* Instead of broad roles, define **specific permissions**.
* Example: `CUSTOMER_READ`, `CUSTOMER_WRITE`, `ORDER_DELETE`.
* More flexible but requires more management.

---

## 6.3 Securing Endpoints with Spring Security

Spring Security is the **de facto library** in Spring Boot for authentication & authorization.

### Example: Securing APIs

```java
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // disable CSRF for APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/customers/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
            )
            .httpBasic(); // enable Basic Auth
        return http.build();
    }
}
```

👉 Later, you can switch `.httpBasic()` with `.oauth2Login()` or custom JWT filter.

---

## 6.4 CORS (Cross-Origin Resource Sharing)

### What is it?

CORS = **browser security policy**.
By default, a frontend app (e.g., React running at `http://localhost:3000`) **cannot call** a backend API at `http://localhost:8080` due to **same-origin policy**.

CORS tells the browser which domains are allowed.

### Example: Allow CORS in Spring Boot

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET","POST","PUT","DELETE");
    }
}
```

Now your frontend at `localhost:3000` can call your backend at `8080`.

---

# ✅ Security Summary

* **Authentication** = who you are. Options:

  * Basic Auth (simple, not secure enough)
  * JWT (stateless, modern)
  * OAuth2/OpenID (delegated, third-party login)
* **Authorization** = what you can do (RBAC or fine-grained permissions).
* **Spring Security** = main library to secure endpoints.
* **CORS** = allow safe cross-domain frontend-backend communication.

---
Awesome 👍 now we’re on **Testing REST APIs**, which is the key to building **reliable, production-grade systems**.

Testing ensures your REST APIs **work as expected**, even when the database, external services, or edge cases come into play.

We’ll go step by step 👇

---

# 7. Testing REST APIs

---

## 7.1 Unit Testing Controllers (with `MockMvc`)

### What is Unit Testing?

* Testing **a small unit** (e.g., a single controller method) in isolation.
* We don’t hit a real DB or network → just test **logic + HTTP mapping**.

### What is `MockMvc`?

* Spring’s **mock HTTP layer**.
* Lets you simulate API calls (`GET`, `POST`, etc.) **without starting a server**.

#### Example: Testing a CustomerController

```java
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService; // mock dependency

    @Test
    void testGetCustomer() throws Exception {
        Customer customer = new Customer(1L, "Aisha", "aisha@example.com");
        when(customerService.getCustomer(1L)).thenReturn(customer);

        mockMvc.perform(get("/customers/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Aisha"));
    }
}
```

👉 Here:

* We only test the **controller layer**.
* `CustomerService` is mocked → no DB involved.

---

## 7.2 Integration Testing with `@SpringBootTest`

### What is Integration Testing?

* Tests **multiple layers together**: Controller + Service + Repository + Database.
* Here, we want to check if API → DB works end-to-end.

### Example:

```java
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class CustomerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void testCreateCustomer() throws Exception {
        String newCustomer = """
            { "name": "Aisha", "email": "aisha@example.com" }
        """;

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newCustomer))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists());
    }
}
```

👉 This actually loads the Spring context, starts the real layers, and uses an **in-memory DB (like H2)** by default.

---

## 7.3 Using **Testcontainers** for DB Integration Tests (MySQL)

### The Problem

* Unit tests use **mocked services**.
* Integration tests often use **H2** (in-memory DB), but H2 ≠ MySQL (different behavior).
* In production, we want **real DB testing**.

### Solution: Testcontainers

* **Docker-based library** that spins up a real MySQL/Postgres/etc. just for the test.
* When tests finish → container is destroyed.

### Example: MySQL Testcontainer

Add dependency:

```xml
<dependency>
  <groupId>org.testcontainers</groupId>
  <artifactId>mysql</artifactId>
  <version>1.19.7</version>
  <scope>test</scope>
</dependency>
```

Integration test:

```java
@SpringBootTest
@Testcontainers
class CustomerRepositoryTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");

    @Autowired
    private CustomerRepository repository;

    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Test
    void testSaveAndFindCustomer() {
        Customer c = new Customer(null, "Aisha", "aisha@example.com");
        repository.save(c);

        Optional<Customer> found = repository.findById(c.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Aisha");
    }
}
```

👉 Here:

* Spring Boot connects to **real MySQL in Docker**.
* You test exactly how production DB behaves.

---

## 7.4 API Testing Tools (Postman)

### What is Postman?

* A **desktop app** for testing APIs manually.
* Lets you send `GET/POST/PUT/DELETE` requests with headers, params, body.
* Useful for **exploring APIs quickly** before frontend exists.

### Example workflow:

1. Create a `POST /customers` request in Postman.
2. Add `Content-Type: application/json`.
3. Body:

   ```json
   {
     "name": "Aisha",
     "email": "aisha@example.com"
   }
   ```
4. Send → Check response status `201 Created`.
5. Save requests into a **collection** (like test scripts).

👉 Bonus: Postman has **automated test scripts** (like mini-integration tests). Example:

```js
pm.test("Status code is 201", function () {
    pm.response.to.have.status(201);
});
```

---

# ✅ Summary of Testing REST APIs

* **Unit Testing (`MockMvc`)** → test controllers in isolation.
* **Integration Testing (`@SpringBootTest`)** → test full flow with Spring context.
* **Testcontainers (MySQL/Postgres/etc.)** → realistic DB tests.
* **Postman** → manual + automated API testing.

---
Perfect timing 🎯 — after building & testing, the next natural step is to make your REST API **discoverable and usable** for others. That’s where **Documentation & API Discovery** comes in. Let’s carefully go through each part:

---

# 8. Documentation & API Discovery

---

## 8.1 Swagger / OpenAPI

### What is OpenAPI?

* **OpenAPI Specification (OAS)** is a **standard format** to describe REST APIs.
* Think of it as a **contract**: it explains endpoints, inputs, outputs, status codes.
* Format: JSON or YAML.

👉 Tools (Swagger UI, Postman, code generators) can use this spec to:

* Auto-generate **docs**
* Generate **client SDKs** (Java, Python, JS…)
* Validate requests/responses

### What is Swagger?

* Originally a toolset for documenting REST APIs.
* Now evolved into **OpenAPI + Swagger UI** ecosystem.
* **Swagger UI** = interactive web UI for exploring APIs.

---

### Adding Swagger (springdoc-openapi) in Spring Boot

Add dependency (Maven):

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.5.0</version>
</dependency>
```

Run app → open:
👉 `http://localhost:8080/swagger-ui.html`

You’ll see **interactive docs** generated from your controllers.
Example:

```java
@RestController
@RequestMapping("/customers")
class CustomerController {

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return new Customer(id, "Aisha", "aisha@example.com");
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer c) {
        return c; // simplified
    }
}
```

Automatically produces docs:

* `GET /customers/{id}` → returns Customer
* `POST /customers` → consumes/produces JSON

---

## 8.2 Generating API Docs Automatically

Springdoc scans:

* `@RestController`, `@RequestMapping`
* Method annotations (`@GetMapping`, `@PostMapping`)
* DTOs (`@Schema`, `@Parameter`, etc.)

### Example: Adding Metadata

```java
@Operation(summary = "Find customer by ID", description = "Returns a single customer")
@ApiResponse(responseCode = "200", description = "Found the customer")
@ApiResponse(responseCode = "404", description = "Customer not found")
@GetMapping("/{id}")
public Customer getCustomer(@PathVariable Long id) {
    return service.findById(id);
}
```

Now docs are more **human-friendly**.

---

## 8.3 Writing Clear API Contracts

An **API contract** is the **formal agreement** between backend and client.

Principles:

* Always define **input schema** (what the client must send).
* Define **response schema** (what server returns).
* List **status codes** and error formats.

👉 Example contract in OpenAPI (YAML):

```yaml
paths:
  /customers/{id}:
    get:
      summary: Get customer by ID
      parameters:
        - in: path
          name: id
          required: true
          schema:
            type: integer
      responses:
        "200":
          description: Customer found
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Customer'
        "404":
          description: Customer not found
components:
  schemas:
    Customer:
      type: object
      properties:
        id: { type: integer }
        name: { type: string }
        email: { type: string }
```

---

## 8.4 API Versioning Strategies & Backward Compatibility

Over time, APIs **evolve**. But clients may still depend on old versions.
So we need **versioning**.

### Common Strategies:

1. **URI Versioning** (most common)

```
/api/v1/customers
/api/v2/customers
```

2. **Header-based Versioning**

```
GET /customers
Accept: application/vnd.myapp.v2+json
```

3. **Query Parameter Versioning**

```
/customers?version=2
```

4. **Content Negotiation** (less common today)

* Different content type for each version.

👉 Best Practice:

* Use **URI versioning** for simplicity.
* Keep old versions alive until clients migrate.
* Clearly document **deprecation policy**.

---

# ✅ Documentation & Discovery Summary

* **Swagger/OpenAPI** = standard way to describe APIs.
* **springdoc-openapi** generates **interactive docs** automatically.
* Add **metadata annotations** for clarity (`@Operation`, `@ApiResponse`).
* Write clear **API contracts**: inputs, outputs, status codes.
* Plan for **versioning**: usually `/v1/...` → `/v2/...`.

---

