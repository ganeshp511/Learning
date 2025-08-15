# Spring Boot Annotations Used in This Project

This document provides detailed explanations and practical examples of all annotations found in this project's codebase. These annotations are critical for Spring Boot, JPA, and Lombok functionality.

---

## 1. JPA (Jakarta Persistence API) Annotations

- **@Entity**  
  Declares a class as a JPA entity mapped to a database table. Required for ORM (Object-Relational Mapping).
  
  _Example:_
  ```java
  @Entity
  public class Product { ... }
  ```

- **@Table(name = "...")**  
  Specifies the actual table name in the database. Optional; defaults to class name if not present.
  
  _Example:_
  ```java
  @Table(name = "products")
  public class Product { ... }
  ```

- **@Id**  
  Marks a field as the primary key of the entity.
  
  _Example:_
  ```java
  @Id
  private Long id;
  ```

- **@GeneratedValue(strategy = ...)**  
  Specifies the strategy for primary key generation (e.g., AUTO, IDENTITY).
  
  _Example:_
  ```java
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  ```

- **@ManyToOne**  
  Defines a many-to-one relationship between two entities (e.g., many products belong to one category).
  
  _Example:_
  ```java
  @ManyToOne
  private Category category;
  ```

- **@Enumerated(EnumType.STRING)**  
  Stores enum values as strings in the database instead of ordinal numbers.
  
  _Example:_
  ```java
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;
  ```

- **@Column(unique = true, nullable = false)**  
  Adds constraints to a column (e.g., must be unique, cannot be null).
  
  _Example:_
  ```java
  @Column(unique = true, nullable = false)
  private String email;
  ```

---

## 2. Lombok Annotations

- **@Data**  
  Generates getters, setters, toString, equals, and hashCode methods automatically. Reduces boilerplate code.
  
  _Example:_
  ```java
  @Data
  public class ProductDTO { ... }
  ```

- **@Getter** / **@Setter**  
  Generates getter/setter methods for all fields or for the annotated field/class.
  
  _Example:_
  ```java
  @Getter
  @Setter
  public class ProductDTO { ... }
  ```

---

## 3. Spring Web Annotations

- **@RestController**  
  Marks a class as a RESTful web controller. The methods return data (usually JSON), not views.
  
  _Example:_
  ```java
  @RestController
  public class AdminController { ... }
  ```

- **@RequestMapping("/path")**  
  Maps HTTP requests to handler classes or methods. Can specify path and HTTP method.
  
  _Example:_
  ```java
  @RequestMapping("/api/admin")
  public class AdminController { ... }
  ```

- **@PostMapping("/path")**  
  Shortcut for @RequestMapping(method = RequestMethod.POST). Maps HTTP POST requests.
  
  _Example:_
  ```java
  @PostMapping("/company")
  public ResponseEntity<?> createCompany(@RequestBody CompanyDTO dto) { ... }
  ```

- **@Autowired**  
  Injects dependencies automatically by type.
  
  _Example:_
  ```java
  @Autowired
  private AdminService adminService;
  ```

- **@RequestBody**  
  Binds the HTTP request body to a method parameter.
  
  _Example:_
  ```java
  public ResponseEntity<?> createCompany(@RequestBody CompanyDTO dto) { ... }
  ```

---

## 4. Spring Service & Transactional Annotations

- **@Service**  
  Marks a class as a service provider (business logic layer). Used for auto-detection and dependency injection.
  
  _Example:_
  ```java
  @Service
  public class AdminService { ... }
  ```

- **@Transactional**  
  Declares that methods in the class should run within a database transaction. Ensures data consistency.
  
  _Example:_
  ```java
  @Transactional
  public class AdminService { ... }
  ```

---

_This document covers all annotations found in the codebase, with explanations and code samples for clarity._