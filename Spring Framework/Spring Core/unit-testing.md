1. **What is JUnit (Basics)**
2. **JUnit 5 essentials** (annotations, assertions, lifecycle)
3. **Integrating with Spring Boot** (`@SpringBootTest`, `@DataJpaTest`, etc.)
4. **Mocking with Mockito** (`@MockBean`, `@Mock`, `@InjectMocks`)
5. **Testing REST APIs** (MockMvc, WebTestClient)
6. **Slice testing** (Web, JPA, Service layers separately)
7. **Advanced concepts**: testcontainers (real DB in tests), parameterized tests, test utilities, CI/CD integration

---

# 1️⃣ Basics: What is JUnit?

* **JUnit** is the de facto standard **unit testing framework for Java**.
* **JUnit 5** is the modern version (JUnit Jupiter API).
* It allows us to write tests that **verify logic** automatically.
* Works standalone or integrated with Spring Boot.

---

# 2️⃣ JUnit 5 Essentials

### ✅ Key annotations

| Annotation     | Purpose                            |
| -------------- | ---------------------------------- |
| `@Test`        | Marks a test method                |
| `@BeforeEach`  | Run before every test              |
| `@AfterEach`   | Run after every test               |
| `@BeforeAll`   | Run once before all tests (static) |
| `@AfterAll`    | Run once after all tests (static)  |
| `@Disabled`    | Skips a test                       |
| `@DisplayName` | Custom test name                   |

### ✅ Assertions

* `assertEquals(expected, actual)`
* `assertTrue(condition)`
* `assertThrows(Exception.class, () -> {...})`
* `assertAll(...)` → group multiple assertions

Great question 👌 — let’s break down **Assertions** in testing (JUnit + Spring Boot context).

---

## 🔹 What are Assertions?

An **assertion** is a **statement in a test** that checks whether a condition is **true**.
If the assertion passes → test continues.
If it fails → the test fails immediately (JUnit marks it red ❌).

Assertions are the **core of unit testing**: they validate that your code produces the expected result.

Think of them as:

> *“Given these inputs, I expect this output. If not → fail.”*

---

## 🔹 Types of Assertions

### 1. **JUnit Jupiter (JUnit 5) Assertions**

Provided by `org.junit.jupiter.api.Assertions`.

Examples:

```java
import static org.junit.jupiter.api.Assertions.*;

@Test
void testBasicAssertions() {
    int sum = 2 + 3;

    assertEquals(5, sum);              // expected == actual
    assertNotEquals(6, sum);           // expected != actual
    assertTrue(sum > 0);               // condition must be true
    assertFalse(sum < 0);              // condition must be false
    assertNotNull(sum);                // must not be null
    assertThrows(ArithmeticException.class, () -> {
        int x = 1 / 0;
    });                                // must throw exception
}
```

---

### 2. **Grouped Assertions**

Check multiple things at once → report all failures.

```java
@Test
void testGroupedAssertions() {
    int num = 10;
    assertAll("number checks",
        () -> assertTrue(num > 0),
        () -> assertEquals(10, num),
        () -> assertNotEquals(5, num)
    );
}
```

---

### 3. **AssertJ (Fluent Assertions)**

Spring Boot’s `spring-boot-starter-test` includes **AssertJ**, which gives **fluent, readable assertions**.

```java
import static org.assertj.core.api.Assertions.assertThat;

@Test
void testWithAssertJ() {
    String name = "Spring Boot";

    assertThat(name)
        .isNotNull()
        .startsWith("Spring")
        .endsWith("Boot")
        .contains("Boot");
}
```

---

### 4. **Hamcrest Assertions** (less common now)

```java
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Test
void testWithHamcrest() {
    String name = "JUnit";

    assertThat(name, is("JUnit"));
    assertThat(name, startsWith("JU"));
    assertThat(name, notNullValue());
}
```

---

## 🔹 Why Assertions are Important?

1. **Automated validation** → you don’t need to print and check manually.
2. **Clear failures** → JUnit shows exactly which assertion failed.
3. **Confidence** → ensures code behaves as expected after changes.
4. **Documentation** → tests + assertions describe intended behavior.

---

## 🔹 Example in a Spring Boot Test

Let’s say you’re testing a `ProductService`:

```java
@Test
void testGetProduct() {
    Product product = productService.getProduct(1L);

    assertNotNull(product);
    assertEquals(1L, product.getId());
    assertThat(product.getName()).isEqualTo("Laptop");
}
```

---

✅ **Summary**:

* **Assertions = checks inside tests**.
* If assertion fails → test fails.
* In JUnit → `assertEquals`, `assertTrue`, `assertThrows`, etc.
* In Spring Boot → you also get **AssertJ** (`assertThat(...)`) for fluent, readable assertions.

### 📌 Example: Pure JUnit Test

```java
import org.junit.jupiter.api.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Addition should return correct result")
    void testAddition() {
        int result = calculator.add(2, 3);
        Assertions.assertEquals(5, result, "2+3 should be 5");
    }

    @Test
    void testDivisionByZero() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10, 0);
        });
    }
}
```

---

# 3️⃣ JUnit + Spring Boot Integration

Spring Boot gives **extra testing power** by loading the application context, injecting beans, and mocking automatically.

### ✅ Add dependencies in `pom.xml`

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-test</artifactId>
  <scope>test</scope>
</dependency>
```

Includes: JUnit 5, Mockito, Hamcrest, JSONassert, AssertJ.

---

### ✅ Example: `@SpringBootTest`

Loads the **full Spring context**.

```java
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        // just ensures Spring context loads
        assertThat(1 + 1).isEqualTo(2);
    }
}
```

---

# 4️⃣ Mocking with Mockito in Spring Boot

### ✅ Example: Unit Test with `@MockBean`

Service depends on a repository → mock repository.

```java
@Service
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo) { this.repo = repo; }

    public Product getProduct(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
```

### Test:

```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository repo;

    @Test
    void testGetProduct() {
        Product mock = new Product(1L, "Laptop");
        when(repo.findById(1L)).thenReturn(Optional.of(mock));

        Product result = productService.getProduct(1L);

        assertThat(result.getName()).isEqualTo("Laptop");
        verify(repo).findById(1L); // ensure repo called
    }
}
```

---

# 5️⃣ Testing REST APIs

### ✅ Example: `MockMvc`

```java
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetProductEndpoint() throws Exception {
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"));
    }
}
```

---

# 6️⃣ Slice Testing

Spring Boot supports **slice annotations** to test only one layer:

* `@DataJpaTest` → only JPA repositories (with H2 in-memory DB by default).
* `@WebMvcTest` → only MVC controllers.
* `@JsonTest` → JSON serialization/deserialization.
* `@DataMongoTest` → Mongo repositories.

### ✅ Example: `@DataJpaTest`

```java
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repo;

    @Test
    void testSaveAndFind() {
        Product product = new Product(null, "Phone");
        repo.save(product);

        Product found = repo.findByName("Phone").get();
        assertThat(found.getName()).isEqualTo("Phone");
    }
}
```

---

# 7️⃣ Advanced Testing Concepts

### ✅ 7.1 Parameterized Tests

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorParamTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6})
    void testEvenNumbers(int number) {
        assertThat(number % 2).isZero();
    }
}
```

---

### ✅ 7.2 TestContainers (Real DB in tests)

Instead of H2 (which differs from prod DB), use **Testcontainers** to run PostgreSQL/MySQL in Docker for tests.

```xml
<dependency>
  <groupId>org.testcontainers</groupId>
  <artifactId>postgresql</artifactId>
  <scope>test</scope>
</dependency>
```

```java
@Testcontainers
@SpringBootTest
class ProductRepositoryContainerTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    @DynamicPropertySource
    static void config(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired ProductRepository repo;

    @Test
    void testSave() {
        repo.save(new Product(null, "Tablet"));
        assertThat(repo.findAll()).isNotEmpty();
    }
}
```

---

### ✅ 7.3 CI/CD Integration

* Run `mvn test` (or `gradle test`) in pipelines.
* Generate coverage with `jacoco`.
* Ensure tests run **headless** (no manual DB needed → H2/Testcontainers).
* Run smoke tests with `@SpringBootTest(webEnvironment = RANDOM_PORT)` and `TestRestTemplate`.

---

# 🔑 Summary

* **Basic JUnit 5** → annotations, assertions, lifecycle.
* **Spring Boot Integration** → `@SpringBootTest`, `@MockBean`, slices.
* **REST testing** → MockMvc / WebTestClient.
* **Advanced** → Parameterized tests, Testcontainers, CI/CD best practices.

👉 You now have a toolbox to write **unit tests, integration tests, and end-to-end tests** in Spring Boot.

---
Got it 👍 You want to measure **code coverage** in your Spring Boot project using **IntelliJ IDEA** and also generate a **JaCoCo coverage report**. Let’s break this down step by step.

---

## ✅ Running Code Coverage in IntelliJ IDEA

IntelliJ has built-in coverage support:

1. **Run Tests with Coverage**

   * Right-click your **test class** or the whole **test folder**.
   * Select **Run 'Tests' with Coverage**.
   * IntelliJ will run tests and show coverage results in the editor (highlighting lines covered in green/red).

2. **View Coverage Report**

   * After running coverage, go to:

     ```
     Run → Show Code Coverage Data
     ```
   * You’ll see a summary of coverage (classes, methods, lines).

⚠️ Limitation: IntelliJ’s built-in coverage is **not JaCoCo** by default, it often uses its own coverage runner.

---

## ✅ Using JaCoCo in Spring Boot

For proper **Maven/Gradle reports** (used in CI/CD, SonarQube, etc.), you should configure **JaCoCo**.

### **If using Maven**

Add JaCoCo plugin in `pom.xml`:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.11</version> <!-- check latest -->
            <executions>
                <execution>
                    <goals>
                        <goal>prepare-agent</goal>
                    </goals>
                </execution>
                <execution>
                    <id>report</id>
                    <phase>verify</phase>
                    <goals>
                        <goal>report</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

Run:

```bash
mvn clean test
mvn verify
```

➡️ Report generated at:

```
target/site/jacoco/index.html
```

---

### **If using Gradle**

Add JaCoCo plugin in `build.gradle`:

```gradle
plugins {
    id 'java'
    id 'jacoco'
}

jacoco {
    toolVersion = "0.8.11" // check latest
}

test {
    useJUnitPlatform()
    finalizedBy jacocoTestReport
}

jacocoTestReport {
    dependsOn test
    reports {
        xml.required = true
        csv.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}
```

Run:

```bash
./gradlew test jacocoTestReport
```

➡️ Report generated at:

```
build/jacocoHtml/index.html
```

---

## 🔄 IntelliJ + JaCoCo Integration

* IntelliJ itself won’t show JaCoCo’s report inside the IDE, but you can:

  * Run tests normally in IntelliJ.
  * Generate JaCoCo reports with Maven/Gradle as shown above.
  * Open the `index.html` report in a browser.

---

✅ **Summary**

* Use **Run with Coverage** in IntelliJ for quick local coverage feedback.
* Use **JaCoCo Maven/Gradle plugin** for official reports (good for CI/CD & SonarQube).
* Reports are in `target/site/jacoco/` (Maven) or `build/jacocoHtml/` (Gradle).

---

Do you want me to also show you **how to merge IntelliJ coverage results with JaCoCo reports**, or just keep IntelliJ for local and JaCoCo for builds?
