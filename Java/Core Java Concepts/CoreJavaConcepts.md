# Core Java Concepts

---

## Packages & import

**Packages** are namespaces that organize related classes and interfaces, helping to avoid naming conflicts and making code easier to maintain.  
**import** statements allow you to use classes from other packages without specifying their full names.

**Example:**
```java
package com.example.utils;

import java.util.List; // Importing List from java.util package

public class MyClass {
    // ...
}
```
**Gotcha:** If two classes with the same name are imported from different packages, you must use their fully qualified names.

---

## Varargs

**Varargs** (variable-length arguments) let you pass zero or more arguments to a method. Declared with `...` after the type.

**Example:**
```java
public void printNumbers(int... numbers) {
    for (int n : numbers) {
        System.out.println(n);
    }
}
```
**Gotcha:** Only one varargs parameter is allowed per method, and it must be the last parameter.

---

## Static & Instance Blocks

- **Static blocks** run once when the class is loaded. Used for static initialization.
- **Instance blocks** run every time an object is created, before the constructor.

**Example:**
```java
class Example {
    static {
        System.out.println("Static block");
    }
    {
        System.out.println("Instance block");
    }
    public Example() {
        System.out.println("Constructor");
    }
}
```
**Output:**
```
Static block
Instance block
Constructor
```
**Gotcha:** Static blocks can’t access instance variables directly.

---

## Enum (Enums with methods)

**Enums** define a fixed set of constants. Enums can have fields, constructors, and methods.

**Example:**
```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY;

    public boolean isWeekend() {
        return this == SATURDAY || this == SUNDAY;
    }
}
```
**Gotcha:** Enum constructors are always private.

---

## Wrapper Classes (Integer, Double, etc.)

**Wrapper classes** provide object representations for primitive types (e.g., `int` → `Integer`). Useful for collections and utility methods.

**Example:**
```java
Integer i = Integer.valueOf(5);
int n = i.intValue();
```
**Gotcha:** Autoboxing/unboxing can cause `NullPointerException` if a null wrapper is unboxed.

---

## Anonymous & Inner Classes

- **Inner classes** are defined within another class.
- **Anonymous classes** are unnamed classes used for one-off implementations, often for interfaces or abstract classes.

**Example:**
```java
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Running!");
    }
};
```
**Gotcha:** Anonymous classes can access final or effectively final variables from the enclosing scope.

---

## Java Memory Model (Heap, Stack, GC basics)

- **Heap:** Stores objects and instance variables. Shared among all threads.
- **Stack:** Stores method calls and local variables. Each thread has its own stack.
- **Garbage Collection (GC):** Java automatically frees memory by removing objects that are no longer referenced.

**Gotcha:** Memory leaks can still occur if references are unintentionally held (e.g., static collections).