Here’s your **✅ Java Generics Cheat Sheet** — a **1-page consolidated summary** covering **everything from basics to advanced**:

---

# ✅ Java Generics Cheat Sheet (From Basics to Advanced)

---

## ✅ 1. **Introduction to Generics**

| Feature         | Description                                              |
| --------------- | -------------------------------------------------------- |
| What            | Type-safe templates for classes, methods, and interfaces |
| Why             | Prevent `ClassCastException`, enable code reuse          |
| Before Generics | Used `Object`, needed casting                            |
| Syntax          | `List<String>`, `Map<K, V>`, `<T> void method(T item)`   |

---

## ✅ 2. **Generic Classes**

```java
class Box<T> {
    T value;
    void set(T val) { value = val; }
    T get() { return value; }
}
```

✅ Multiple parameters: `class Pair<K, V>`

---

## ✅ 3. **Generic Methods**

```java
public <T> void printArray(T[] arr)
```

✅ Can be static
✅ Compiler infers type from arguments

---

## ✅ 4. **Bounded Type Parameters**

```java
<T extends Number>       // upper bound
<T extends Number & Comparable<T>>  // multiple bounds
```

Used for mathematical ops, comparisons.

---

## ✅ 5. **Wildcards (`?`)**

| Type            | Use                        |
| --------------- | -------------------------- |
| `<?>`           | Unknown type               |
| `<? extends T>` | Covariant (read-only)      |
| `<? super T>`   | Contravariant (write-only) |

🧠 **PECS Rule**: *Producer Extends, Consumer Super*

---

## ✅ 6. **Generics with Collections**

* Type-safe: `List<String>`, `Map<String, Integer>`
* Works with enhanced for-loop
* Sorting via `Comparator<T>` and `Comparable<T>`

---

## ✅ 7. **Type Erasure**

| Limitation                   | Reason                                  |
| ---------------------------- | --------------------------------------- |
| No `new T[]`                 | Type info erased                        |
| No `instanceof List<String>` | Runtime doesn’t know `String`           |
| No primitive generics        | Use wrapper types                       |
| Bridge methods               | Maintain method overriding post-erasure |

---

## ✅ 8. **Generic Arrays and Varargs**

* ❌ `new T[]` not allowed
* ✅ Use `Array.newInstance(Class<T>, size)`
* Use `@SafeVarargs` with static/final/private methods

---

## ✅ 9. **Inheritance & Subtyping**

| Rule                            | Notes                  |
| ------------------------------- | ---------------------- |
| `List<String>` ≠ `List<Object>` | Invariant              |
| Covariant                       | `<? extends T>` (read) |
| Contravariant                   | `<? super T>` (write)  |

✅ Abstract classes/interfaces can be generic.

---

## ✅ 10. **Advanced Use Cases**

| Concept            | Use                            |
| ------------------ | ------------------------------ |
| Custom collections | `Stack<T>`, `Box<T>`           |
| Nested generics    | `Map<String, List<Integer>>`   |
| Fluent APIs        | `T extends Self<T>`            |
| Frameworks         | Spring: `JpaRepository<T, ID>` |

---

## ✅ 11. **Generic Interfaces & Abstract Classes**

```java
interface Repository<T> {
  T findById(int id);
}
```

```java
abstract class Service<T> {
  abstract void process(T input);
}
```

✅ Reusable and type-safe service layers

---

## ✅ 12. **Restrictions & Limitations**

| ❌ Not Allowed        | ✅ Reason             |
| -------------------- | -------------------- |
| `new T()`            | Erasure              |
| `List<int>`          | Use `Integer`        |
| `catch (T e)`        | Type unknown         |
| `instanceof List<T>` | No runtime type info |

---

## ✅ 13. **Generic Utility Classes**

```java
public static <T> T getFirst(List<T> list) { ... }
```

✅ Used in Guava, Apache Commons, etc.

---

## ✅ 14. **Best Practices**

| Tip                                         | Description                     |
| ------------------------------------------- | ------------------------------- |
| Use type names like `T`, `E`, `K`, `V`      | Follow conventions              |
| Avoid raw types                             | Use `List<T>` instead of `List` |
| Use `? extends` to read, `? super` to write | Follow PECS                     |
| Don’t overuse wildcards                     | Keep APIs clean and readable    |

---

Excellent! Let’s wrap up with the **practical and interview-focused side** of Java Generics — where it **really matters** in your software career.

---

## ✅ **15. Interview and Real-World Scenarios**

---

## 🎯 A. Common Interview Questions on Generics

These test your **understanding, edge cases, and practical application**.

---

### 🔶 1. **Why can’t you create a generic array (`new T[]`) in Java?**

➡️ Because of **type erasure** — the runtime doesn’t know what `T` is, and arrays must know their type at runtime (they are reifiable).

---

### 🔶 2. **What is type erasure? How does it affect generics?**

➡️ At compile time, Java erases type info to stay backward-compatible with older versions.
➡️ This causes limitations like:

* No `new T()`,
* No `instanceof List<T>`,
* No generic exceptions

---

### 🔶 3. **Explain `<? extends T>` vs `<? super T>` (PECS Rule)**

* **`<? extends T>`**: Covariant → *read* only (like a producer)
* **`<? super T>`**: Contravariant → *write* only (like a consumer)

> 📌 **PECS** = **P**roducer → **E**xtends, **C**onsumer → **S**uper

---

### 🔶 4. **Can you override a method with a generic return type? What are bridge methods?**

Yes, but if the return types differ due to type erasure, the compiler generates a **bridge method** to maintain polymorphism.

---

### 🔶 5. **Difference between generic class and generic method?**

* **Generic class**: Declares `<T>` at class level — available to all members
* **Generic method**: Declares `<T>` only in that method — reusable in non-generic classes

---

### 🔶 6. **Why are generics not available at runtime?**

➡️ Due to **type erasure**, all generic type info is removed at compile time to preserve backward compatibility with JVM.

---

## 🏢 B. Real-World Use Cases of Generics

---

### ✅ 1. **Type-Safe DAO/Repository Layer**

```java
public interface CrudRepository<T, ID> {
    T findById(ID id);
    void save(T entity);
}
```

```java
public class UserRepository implements CrudRepository<User, Integer> { ... }
```

✅ This avoids duplicating code for each entity: `User`, `Product`, `Order`...

---

### ✅ 2. **Generic Service Layer**

```java
public class GenericService<T> {
    public void process(T data) {
        System.out.println("Processing: " + data);
    }
}
```

Then extend for specific types:

```java
public class EmailService extends GenericService<Email> { }
public class OrderService extends GenericService<Order> { }
```

✅ Reduces repeated logic in service layers.

---

### ✅ 3. **Generic Factory**

```java
public class Factory<T> {
    private Class<T> clazz;

    public Factory(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T createInstance() throws Exception {
        return clazz.getDeclaredConstructor().newInstance();
    }
}
```

✅ Usage:

```java
Factory<User> userFactory = new Factory<>(User.class);
User u = userFactory.createInstance();
```

---

### ✅ 4. **Generic Fluent Builder**

```java
public class PersonBuilder<T extends PersonBuilder<T>> {
    protected String name;

    public T setName(String name) {
        this.name = name;
        return self();
    }

    protected T self() {
        return (T) this;
    }

    public Person build() {
        return new Person(name);
    }
}
```

✅ Extend:

```java
public class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {
    private String dept;

    public EmployeeBuilder setDept(String dept) {
        this.dept = dept;
        return this;
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}
```

➡️ Chain methods like:

```java
Employee e = new EmployeeBuilder()
                .setName("Ganesh")
                .setDept("Tech")
                .build();
```

---

## 💼 Summary: Why Generics Matter in Interviews and Real Life

| Context      | Example                  | Why It Matters           |
| ------------ | ------------------------ | ------------------------ |
| Interview Qs | PECS, Erasure, Wildcards | Tests deep understanding |
| Code Reuse   | `Repository<T>`          | Avoids duplication       |
| Flexibility  | Builders, Factories      | Scalable design          |
| Type Safety  | Collections, APIs        | Prevents runtime errors  |

---

