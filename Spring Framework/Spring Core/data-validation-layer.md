# 🔹 What is the Data Validation Layer?

The **data validation layer** is the part of your application that ensures incoming or internal data is **valid, consistent, and safe** *before* it flows deeper into the system (DB, business logic, etc.).

* It protects the system from **bad inputs** (user mistakes, malicious input).
* It enforces **business rules** (e.g., “age must be ≥ 18”, “email must be valid”).
* In Spring Boot, it typically sits **between the Controller and Service layer** (but validation can also happen deeper).

---

# 🔹 Where Does Validation Happen in Spring Boot?

1. **At the API boundary (Controller Layer)**

   * Validate incoming REST request payloads (`@RequestBody`, query params).
   * Example: check if email is valid, price is positive, name is not empty.

2. **At the Persistence Layer (Entity/Database constraints)**

   * JPA annotations ensure DB consistency (`@Column(nullable = false)`, `@Size`, etc.).

3. **At the Service Layer (business validation)**

   * Example: “user cannot buy more than 5 products per day.”

👉 These form the **data validation layer** as a whole.

---

# 🔹 Tools for Data Validation in Spring Boot

Spring Boot uses **Jakarta Bean Validation (JSR-380)** with **Hibernate Validator** as the default provider.

### Common Annotations

* `@NotNull`, `@NotEmpty`, `@NotBlank` → required fields
* `@Size(min, max)` → string/collection length
* `@Email` → valid email format
* `@Min`, `@Max` → numeric bounds
* `@Positive`, `@PositiveOrZero`, `@Negative` → numeric sign
* `@Pattern(regex)` → regex validation
* `@Past`, `@Future` → date validation

---

# 🔹 Example: DTO Validation in Controller

```java
import jakarta.validation.constraints.*;

public class UserDto {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    // getters and setters
}
```

Controller:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto user) {
        // If validation fails, Spring throws MethodArgumentNotValidException
        return ResponseEntity.ok("User created: " + user.getName());
    }
}
```

If you send invalid data (e.g., empty name), Spring Boot automatically returns a **400 Bad Request** with an error response.

---

# 🔹 Custom Validation

Sometimes built-in annotations aren’t enough.
Example: A field `password` must contain at least one uppercase, one digit, one symbol.

1. Define custom annotation:

```java
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Invalid password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

2. Create validator:

```java
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).+$");
    }
}
```

3. Use it:

```java
public class RegisterDto {
    @ValidPassword
    private String password;
}
```

---

# 🔹 Validation in Persistence Layer (Entity)

```java
@Entity
public class Product {

    @Id @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @Positive
    private double price;
}
```

* JPA + Hibernate ensures constraints when persisting.
* If you try to save invalid data, it throws `ConstraintViolationException`.

---

# 🔹 Validation in Service Layer

Here you validate **business logic rules** that go beyond field-level constraints.

Example:

```java
@Service
public class OrderService {
    public void placeOrder(Order order) {
        if (order.getItems().size() > 5) {
            throw new IllegalArgumentException("Max 5 items allowed per order");
        }
        // save order...
    }
}
```

---

# 🔹 Centralized Error Handling

Spring Boot lets you customize error responses using `@ControllerAdvice`.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
```

👉 This way, clients get structured error messages.

---

# 🔹 Summary

**Data Validation Layer = enforcing correctness of data across all layers**:

1. **Controller Layer** → Input validation with `@Valid` and annotations.
2. **Persistence Layer** → Entity constraints (JPA + Hibernate Validator).
3. **Service Layer** → Complex business rules.
4. **Error Handling** → Centralized with `@ControllerAdvice`.

---

✅ By layering validation, you **catch bad data early** and **ensure system integrity**.

---
