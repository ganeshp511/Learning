Absolutely! Let's go **deep** into **Java 8 Functional Programming**, which is a cornerstone of modern Java and essential for writing clean, expressive, and modular code.

---

## 🔶 13. Java 8 Functional Programming – Detailed Breakdown

---

### ✅ 1. **Lambda Expressions**

#### 🔷 What is a Lambda?

A lambda expression is a **short block of code** which takes in parameters and returns a value. It's similar to an anonymous function (a function without a name) used to simplify instances of functional interfaces.

#### 🔷 Syntax:

```java
(parameters) -> expression
```

Or for multi-line:

```java
(parameters) -> {
   // multiple statements
   return value;
}
```

#### 🔷 Example:

```java
List<String> names = Arrays.asList("Ganesh", "Sneha", "Tommy");
names.forEach(name -> System.out.println(name));
```

#### 🔷 Use Cases:

* Replace anonymous inner classes
* Use in collections (`forEach`, `sort`, `filter`, etc.)
* Event handling (like in GUI or Web)

---

### ✅ 2. **Functional Interfaces**

#### 🔷 What is a Functional Interface?

An interface with **only one abstract method** is called a **functional interface**.

#### 🔷 Common Built-in Functional Interfaces (from `java.util.function`):

| Interface        | Description                            | Example Lambda                 |
| ---------------- | -------------------------------------- | ------------------------------ |
| `Function<T, R>` | Takes a T and returns an R             | `x -> x.length()`              |
| `Predicate<T>`   | Takes a T and returns `boolean` (test) | `str -> str.isEmpty()`         |
| `Supplier<T>`    | Takes nothing, returns a T             | `() -> new Random().nextInt()` |
| `Consumer<T>`    | Takes a T, returns nothing (consume)   | `x -> System.out.println(x)`   |

#### 🔷 Example:

```java
Predicate<String> isEmpty = str -> str.isEmpty();
System.out.println(isEmpty.test("")); // true
```

#### 🔷 Custom Functional Interface:

```java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}
```

---

### ✅ 3. **Method References**

A method reference is a **shorthand notation** of a lambda expression to call a method.

#### 🔷 Syntax:

```java
ClassName::methodName
```

#### 🔷 Types:

| Type                    | Example                |
| ----------------------- | ---------------------- |
| Static method           | `Math::sqrt`           |
| Instance method         | `"hello"::toUpperCase` |
| Arbitrary object method | `String::toLowerCase`  |
| Constructor reference   | `ArrayList::new`       |

#### 🔷 Example:

```java
List<String> names = Arrays.asList("Tommy", "Ganesh");
names.forEach(System.out::println);
```

---

### ✅ 4. **Default and Static Methods in Interfaces**

Java 8 allows **default methods** in interfaces so that new methods can be added without breaking existing implementations.

#### 🔷 Default Method:

```java
interface Vehicle {
    default void start() {
        System.out.println("Vehicle started");
    }
}
```

#### 🔷 Static Method:

```java
interface Utils {
    static int add(int a, int b) {
        return a + b;
    }
}
```

#### 🔷 Why Useful?

* Interfaces can evolve without breaking existing code
* Reduce need for abstract classes
* Combine behavior in functional pipelines

---

### ✅ 5. **Function Composition**

Function composition means **chaining multiple functions** together.

#### 🔷 With `Function<T, R>` interface:

```java
Function<String, String> trim = String::trim;
Function<String, String> toUpper = String::toUpperCase;

Function<String, String> cleanAndUpper = trim.andThen(toUpper);

System.out.println(cleanAndUpper.apply("  hello  ")); // "HELLO"
```

#### 🔷 `compose()` vs `andThen()`:

| Method    | Order of Execution               |
| --------- | -------------------------------- |
| `compose` | Executes **before** current func |
| `andThen` | Executes **after** current func  |

#### 🔷 Real-World Use:

* Building data transformation pipelines
* Chaining operations in stream processing
* Clean separation of logic

---

## 🧠 Summary:

| Feature               | Purpose                                      |
| --------------------- | -------------------------------------------- |
| Lambda                | Short, concise function syntax               |
| Functional Interfaces | Enable lambda usage (single abstract method) |
| Method References     | Cleaner syntax for calling methods           |
| Default/Static        | Add functionality in interfaces              |
| Function Composition  | Build pipelines by chaining logic            |

---

