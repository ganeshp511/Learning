# Java Task Management Mini Project

This project demonstrates a simple, thread-safe task management system in Java, featuring a command-line interface (CLI) for managing tasks.

---

## Java Concepts Used in This Mini Project

### 1. Class and Object
Java is an object-oriented language. Classes like `TaskModel`, `TaskList`, and `TaskManagerCLI` define blueprints for objects. Objects are instances of these classes, representing tasks and managing them.

### 2. Enum
Used for `TaskStatus` to represent a fixed set of constants (e.g., NOT_STARTED, IN_PROGRESS, COMPLETED) with associated string values for clarity and type safety.

### 3. List Interface and Implementations
`List` is a core Java interface for ordered collections. `CopyOnWriteArrayList` is used for thread-safe task storage, and `ArrayList` is referenced for general list operations.

### 4. Stack and Deque
`Stack` is used to implement undo functionality, allowing the last action to be reverted. `Deque` (with `ArrayDeque`) is used to maintain a history of the last 5 tasks.

### 5. Generics
Java generics (e.g., `List<TaskModel>`) provide type safety and flexibility for collections, ensuring only the correct type of objects are stored.

### 6. Synchronization and Thread Safety
The `synchronized` keyword and thread-safe collections ensure that multiple threads can safely access and modify shared data without causing inconsistencies.

### 7. Exception Handling
Try-catch blocks are used to handle invalid user input (e.g., parsing enum values from strings in the CLI).

### 8. Switch-Case Statement
Used in the CLI to handle user menu choices efficiently and clearly.

### 9. Scanner for User Input
The `Scanner` class is used to read user input from the console, enabling interactive command-line programs.

### 10. Method Overloading and Overriding
Methods like `toString()` are overridden in model classes to provide meaningful string representations of objects.

### 11. Access Modifiers
`private`, `public`, and `final` are used to control visibility and mutability of fields and methods, following encapsulation principles.

### 12. JavaDoc and Inline Comments
JavaDoc comments and inline comments are used throughout the code to document functionality, making the codebase easier to understand and maintain.

### 13. Predicate
A `Predicate<T>` is a functional interface that represents a boolean-valued function of one argument. It is often used for filtering or matching elements in collections.

**Example:**
```java
Predicate<TaskModel> isCompleted = task -> task.getStatus() == TaskStatus.COMPLETED;
```

### 14. Consumer
A `Consumer<T>` is a functional interface that represents an operation that accepts a single input argument and returns no result. It is commonly used for performing actions on each element of a collection.

**Example:**
```java
Consumer<TaskModel> printTask = task -> System.out.println(task);
```

### 15. Functional Interface
A functional interface in Java is an interface with exactly one abstract method. Functional interfaces are the basis for lambda expressions and method references.

**Example:**
```java
@FunctionalInterface
public interface TaskAction {
    void execute(TaskModel task);
}
```

### 16. Functional Programming
Functional programming is a programming paradigm that treats computation as the evaluation of mathematical functions and avoids changing state or mutable data. In Java, this is supported through features like lambda expressions, streams, and functional interfaces, enabling more declarative and concise code.

---

## What is `enum` in Java?

An `enum` in Java is a special data type that enables for a variable to be a set of predefined constants. It is short for "enumeration." Enums are used when you have a fixed set of related constants, such as days of the week, directions, or states.

**Key points:**
- Enums provide type safety (you can't assign any value that's not predefined).
- Enums can have fields, methods, and constructors.
- Enums are more powerful than simple `final static` constants.

**Example:**
```java
public enum DaysOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

DaysOfWeek today = DaysOfWeek.MONDAY;
if (today == DaysOfWeek.MONDAY) {
    System.out.println("Start of the work week!");
}
```

**Gotcha:**
Unlike in some languages, Java enums are full-fledged classes and can have methods and fields. This makes them very flexible for modeling fixed sets of related data.

---

## What is `synchronized` in Java?

`synchronized` is a keyword in Java used to control access to a block of code or method by multiple threads. It ensures that only one thread can execute the synchronized section at a time, preventing data inconsistency and race conditions in multi-threaded environments.

**Key Points:**
- Used for thread safety when multiple threads access shared resources.
- Can be applied to methods or code blocks.
- Locks the object (or class, for static methods) so only one thread can execute the synchronized code at a time.

**Example:**
```java
public synchronized void increment() {
    count++;
}

// Or using a synchronized block
public void increment() {
    synchronized(this) {
        count++;
    }
}
```

**When to use:**
Use `synchronized` when you need to protect shared data from concurrent modification by multiple threads.

