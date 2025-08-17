Excellent 👍 we are now moving from REST **concepts** (HTTP, JSON, URIs) into how to **implement them in Spring Boot**.
As you requested, I’ll introduce each **concept/annotation/class** first in plain words → then show **how it’s used** → then give **examples**.

---

# 2. Building REST APIs with Spring

---

## 2.1 `@RestController` and `@RequestMapping`

### What is a **controller**?

In Spring Boot, a **controller** is a class where you write methods that handle HTTP requests (GET, POST, etc.).
It acts as the “entry point” of your REST API.

### `@RestController`

* A Spring annotation that marks a class as a REST controller.
* It tells Spring: “This class has methods that will handle web requests and return JSON/XML directly.”
* It’s a combination of:

  * `@Controller` (marks a class as a controller)
  * `@ResponseBody` (ensures return values are written directly to the response body as JSON)

### `@RequestMapping`

* Used at **class level** or **method level**.
* Defines the **base path** or the specific path for the resource.

### Example

```java
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping
    public String getAllCustomers() {
        return "Returning all customers";
    }
}
```

* `@RestController` → class is a REST controller
* `@RequestMapping("/customers")` → base URI = `/customers`
* `@GetMapping` → handles `GET /customers`

---

## 2.2 `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@PatchMapping`

These are **specialized shortcuts** for `@RequestMapping` that specify the HTTP method.

* `@GetMapping` → Handles **GET requests** (fetch data).
* `@PostMapping` → Handles **POST requests** (create new resource).
* `@PutMapping` → Handles **PUT requests** (replace full resource).
* `@PatchMapping` → Handles **PATCH requests** (update part of resource).
* `@DeleteMapping` → Handles **DELETE requests** (remove resource).

### Example

```java
@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id) {
        return "Returning product with ID = " + id;
    }

    @PostMapping
    public String createProduct() {
        return "Product created";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id) {
        return "Product " + id + " updated";
    }

    @PatchMapping("/{id}")
    public String partiallyUpdateProduct(@PathVariable Long id) {
        return "Product " + id + " partially updated";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return "Product " + id + " deleted";
    }
}
```

---

## 2.3 Returning JSON with `ResponseEntity`

### What is **ResponseEntity**?

* A class that represents the **full HTTP response**.
* You can control:

  * **Body** (data you return)
  * **Status Code** (200, 201, 404, etc.)
  * **Headers** (e.g., `Location`, `Content-Type`)

### Example

```java
@GetMapping("/{id}")
public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    Product product = new Product(id, "Laptop", 1200.0);

    if (product == null) {
        return ResponseEntity.notFound().build(); // 404
    }

    return ResponseEntity.ok(product); // 200 + JSON body
}
```

If Spring sees a Java object (like `Product`), it automatically converts it into **JSON** using **Jackson** (a JSON library built into Spring Boot).

---

## 2.4 Path Variables vs Query Parameters

### Path Variables

* Defined in the **URI path**.
* Used when the value **identifies a specific resource**.
* Annotation: `@PathVariable`.

Example:

```
GET /customers/42
```

```java
@GetMapping("/{id}")
public String getCustomer(@PathVariable Long id) {
    return "Customer ID = " + id;
}
```

---

### Query Parameters

* Appear after `?` in the URI.
* Used for **filtering, searching, sorting, pagination**, etc.
* Annotation: `@RequestParam`.

Example:

```
GET /customers?status=active&page=2
```

```java
@GetMapping
public String getCustomers(@RequestParam String status,
                           @RequestParam int page) {
    return "Status: " + status + ", Page: " + page;
}
```

---

## 2.5 Request Body (`@RequestBody`) and Validation (`@Valid`)

### Request Body

* The **body** of the HTTP request usually contains JSON data for creating/updating resources.
* Annotation: `@RequestBody` maps JSON → Java object.

Example request:

```http
POST /customers
Content-Type: application/json

{
  "name": "Aisha Khan",
  "email": "aisha@example.com"
}
```

Controller method:

```java
@PostMapping
public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    // customer.name and customer.email populated from JSON
    return ResponseEntity.status(HttpStatus.CREATED).body(customer);
}
```

---
Nice 🚀 now we’re talking about **file uploads** in REST APIs:

* **CSV upload** → common for bulk data import.
* **Image upload** → common for user profiles, product images, etc.

Let’s go step by step.

---

# 🔹 File Upload in REST APIs (Spring Boot)

---

## 1. Basics: Multipart Requests

* In HTTP, file uploads are done with **`multipart/form-data`** content type.
* Each part of the request can contain a file or form field.
* Spring Boot maps uploaded files to **`MultipartFile`** objects.

Example:

```http
POST /upload
Content-Type: multipart/form-data
```

---

## 2. CSV Upload Example

### Controller

```java
@RestController
@RequestMapping("/files")
public class FileUploadController {

    @PostMapping("/upload-csv")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty!");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Each line in CSV
                String[] columns = line.split(",");
                System.out.println(Arrays.toString(columns));
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error reading file!");
        }

        return ResponseEntity.ok("CSV uploaded successfully!");
    }
}
```

👉 Test in **Postman**:

* Method: `POST`
* URL: `http://localhost:8080/files/upload-csv`
* Body → `form-data` → key=`file`, type=`File`, choose a `.csv` file.

---

## 3. Image Upload Example

### Controller

```java
@PostMapping("/upload-image")
public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
    if (image.isEmpty()) {
        return ResponseEntity.badRequest().body("Image is empty!");
    }

    try {
        // Save image to disk
        Path path = Paths.get("uploads/" + image.getOriginalFilename());
        Files.createDirectories(path.getParent());
        Files.write(path, image.getBytes());

        return ResponseEntity.ok("Image uploaded: " + path.toAbsolutePath());
    } catch (IOException e) {
        return ResponseEntity.status(500).body("Error saving image!");
    }
}
```

👉 This saves the uploaded image under `/uploads/`.

---

## 4. Returning Uploaded Images

Sometimes you want to **serve uploaded images** back to clients.

### Example:

```java
@GetMapping("/images/{filename}")
public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
    Path path = Paths.get("uploads/" + filename);
    Resource resource = new UrlResource(path.toUri());

    return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_JPEG) // or detect dynamically
            .body(resource);
}
```

Now clients can access:

```
GET /files/images/myphoto.jpg
```

---

## 5. Advanced Considerations

* **Validation**:

  * Restrict file types (`.csv`, `.jpg`, `.png`).
  * Restrict max file size (`spring.servlet.multipart.max-file-size=5MB`).

* **Storage Options**:

  * Local disk (simple).
  * Database (store `byte[]`, but not ideal for big files).
  * Cloud storage (AWS S3, Google Cloud Storage).

* **CSV Parsing**: Use libraries like **Apache Commons CSV** or **OpenCSV** for better parsing (headers, quotes, etc.).

* **Image Processing**: Sometimes you need to resize/validate images (e.g., using **Thumbnails** or **ImageIO**).

---

# ✅ Summary

* Use `MultipartFile` in Spring Boot for file uploads.
* **CSV Upload** → parse with `BufferedReader` or CSV library.
* **Image Upload** → save to disk/cloud & return URL.
* Add validation (file size, type).
* Serve uploaded images with `Resource`.

---

### Validation

* Use `javax.validation` (Jakarta Bean Validation).
* Annotate fields in your model class.
* Use `@Valid` in controller method.

Example model:

```java
public class Customer {
    @NotBlank
    private String name;

    @Email
    private String email;

    // getters & setters
}
```

Example usage:

```java
@PostMapping
public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
    return ResponseEntity.status(HttpStatus.CREATED).body(customer);
}
```

If validation fails → Spring automatically returns **400 Bad Request** with error details.

---

## 2.6 Exception Handling (`@ControllerAdvice`, `@ExceptionHandler`)

### Why exception handling?

When something goes wrong (e.g., resource not found, invalid input), you want to return a **clean JSON error response** instead of a raw stack trace.

---

### `@ExceptionHandler`

* Used inside a controller to handle exceptions **locally**.

```java
@GetMapping("/{id}")
public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
    if (id == 0) {
        throw new IllegalArgumentException("Invalid ID");
    }
    return ResponseEntity.ok(new Customer("Aisha", "aisha@example.com"));
}

@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<String> handleInvalidArgument(IllegalArgumentException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
}
```

---

### `@ControllerAdvice`

* A **global exception handler** that applies to all controllers.
* Central place to handle exceptions consistently.

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
```

---

✅ **Summary of this section**

* `@RestController` + `@RequestMapping` = define REST endpoints.
* HTTP method annotations (`@GetMapping`, `@PostMapping`, etc.) map URIs to methods.
* `ResponseEntity` lets you control **body + status + headers**.
* `@PathVariable` vs `@RequestParam` = path values vs query params.
* `@RequestBody` maps JSON → Java, and `@Valid` adds validation.
* `@ControllerAdvice` + `@ExceptionHandler` = clean error handling.

---
Alright 🚀 we’re now at the **Data & Persistence** layer of building REST APIs in Spring Boot.
This is where your API goes from being “just returning strings” → to working with a **database**.

As always, I’ll introduce each concept first → then give examples.

---

# 3. Data & Persistence

---

## 3.1 Integrating with a Database

### What does “persistence” mean?

* **Persistence** = saving data so it’s not lost when the application restarts.
* In Spring Boot, the most common persistence options are:

  1. **Relational Database** (MySQL, PostgreSQL, H2, etc.) → usually with **Spring Data JPA**.
  2. **NoSQL Database** (MongoDB, Redis, etc.) → with Spring Data MongoDB, Spring Data Redis, etc.

### Spring Data JPA (Relational DBs)

* **JPA (Java Persistence API)** = specification for ORM (object-relational mapping).
* **Hibernate** = popular JPA implementation.
* **Spring Data JPA** = makes database queries easier by auto-generating most SQL.

Flow:

* Define an **Entity** (maps to table).
* Create a **Repository** (interface) → Spring auto-generates queries.
* Use in a **Service/Controller**.

Example (using H2 or MySQL):

```java
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
}
```

```java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Spring generates methods automatically
    List<Customer> findByEmail(String email);
}
```

```java
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAll() {
        return repository.findAll();
    }
}
```

---

### Spring Data MongoDB (NoSQL DBs)

* Works with **document-oriented databases** like MongoDB.
* Similar to JPA, but collections instead of tables.

Example:

```java
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    private String name;
    private String email;
}
```

```java
public interface CustomerRepository extends MongoRepository<Customer, String> {}
```

Both JPA and MongoDB integrate smoothly with Spring Boot (just change dependency + annotations).

---

## 3.2 Mapping DTOs vs Entities

### What is an Entity?

* An **Entity** is a class that maps directly to a database table/collection.
* It usually has annotations like `@Entity` (JPA) or `@Document` (MongoDB).
* Example: `Customer` with fields like `id`, `name`, `email`.

### What is a DTO (Data Transfer Object)?

* A **DTO** is a simpler object used to **transfer data** between layers or across the network (API request/response).
* Purpose: Don’t expose your internal entity structure directly to clients.

Example:

```java
// Entity (stored in DB)
@Entity
public class Customer {
    @Id
    private Long id;
    private String name;
    private String email;
    private String passwordHash; // should NOT be exposed in API
}

// DTO (used in API response)
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}
```

Why use DTOs?

* Security (hide sensitive fields like password).
* Flexibility (you can change DB schema without breaking clients).
* Cleaner API contracts.

---

## 3.3 Using ModelMapper or MapStruct for Object Conversions

When you have Entities and DTOs, you need to **map between them**.
Instead of writing boilerplate getters/setters manually, use libraries:

* **ModelMapper**: Reflection-based, runtime conversion (simpler but slightly slower).
* **MapStruct**: Compile-time code generation (fast, type-safe).

Example with ModelMapper:

```java
ModelMapper modelMapper = new ModelMapper();
CustomerDTO dto = modelMapper.map(customerEntity, CustomerDTO.class);
```

Example with MapStruct:

```java
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO dto);
}
```

---

## 3.4 Pagination & Sorting with `Pageable`

### What is Pagination?

* If you have **thousands of rows**, you don’t want to return all at once.
* Instead, return **chunks (pages)**.
* Example: `/customers?page=0&size=20` → first 20 customers.

### What is Sorting?

* Define ordering of results.
* Example: `/customers?page=0&size=20&sort=name,asc`.

### Spring Data JPA support

```java
@GetMapping
public Page<Customer> getAllCustomers(Pageable pageable) {
    return repository.findAll(pageable);
}
```

Request example:

```
GET /customers?page=1&size=10&sort=name,desc
```

Response includes:

* `content`: actual data
* `totalPages`, `totalElements`, `number`, `size`

---

## 3.5 Filtering Results

Filtering = returning only records that match certain criteria.

### Approaches:

1. **Query Parameters**

   ```
   GET /customers?email=aisha@example.com&active=true
   ```

   ```java
   List<Customer> findByEmailAndActive(String email, boolean active);
   ```

2. **Custom Queries (JPQL/SQL)**

   ```java
   @Query("SELECT c FROM Customer c WHERE c.name LIKE %:name%")
   List<Customer> searchByName(@Param("name") String name);
   ```

3. **Specification API (advanced dynamic queries)**
   Use `JpaSpecificationExecutor` when filtering is complex and dynamic (e.g., multiple optional params).

---

# ✅ Summary of Data & Persistence

* Use **Spring Data JPA** (SQL DB) or **Spring Data MongoDB** (NoSQL DB).
* Separate **Entities** (DB objects) from **DTOs** (API objects).
* Use **ModelMapper/MapStruct** to map between them.
* Support **pagination & sorting** out-of-the-box with `Pageable`.
* Add **filtering** with query params or custom queries.

---
