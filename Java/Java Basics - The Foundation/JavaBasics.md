Great! Let's begin your **Java mastery journey** from the very foundation:

# ✅ **1. Java Basics – The Foundation**

---

## 🔷 What is Java?

**Java** is a **high-level, object-oriented, platform-independent programming language**. It was developed by **James Gosling** at **Sun Microsystems** in **1995**, later acquired by Oracle.

### 🔹 Key Features:

| Feature                  | Description                                          |
| ------------------------ | ---------------------------------------------------- |
| **Platform Independent** | "Write once, run anywhere" (WORA) due to the **JVM** |
| **Object-Oriented**      | Everything is modeled as objects and classes         |
| **Simple & Secure**      | No pointers, automatic memory management             |
| **Robust**               | Strong exception handling and garbage collection     |
| **Multithreaded**        | Supports concurrent programming                      |
| **High Performance**     | With JIT (Just-In-Time) compiler                     |
| **Portable**             | Bytecode can run on any OS with JVM                  |

---

## 🔷 JDK vs JRE vs JVM

| Term                               | Description                               |
| ---------------------------------- | ----------------------------------------- |
| **JVM (Java Virtual Machine)**     | Runs the `.class` files (bytecode)        |
| **JRE (Java Runtime Environment)** | JVM + libraries for running Java programs |
| **JDK (Java Development Kit)**     | JRE + compiler + development tools        |

### 🔽 Diagram:

```
            [ Your Code (.java) ]
                     ↓
               javac (compiler)
                     ↓
             [ Bytecode (.class) ]
                     ↓
                  ┌────────┐
                  │   JVM  │
                  └────────┘
                 /          \
        Core Libraries    Native OS
```

---

## 🔷 Hello World Program

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}
```

### 🔹 Compilation and Execution:

```bash
javac HelloWorld.java   // Compiles to HelloWorld.class
java HelloWorld         // Runs with JVM
```

---

## 🔷 Data Types & Variables

### 🔹 Primitive Data Types:

| Type    | Size    | Example             |
| ------- | ------- | ------------------- |
| byte    | 1 byte  | `byte b = 10;`      |
| short   | 2 bytes | `short s = 100;`    |
| int     | 4 bytes | `int i = 1000;`     |
| long    | 8 bytes | `long l = 123L;`    |
| float   | 4 bytes | `float f = 2.5f;`   |
| double  | 8 bytes | `double d = 5.5;`   |
| char    | 2 bytes | `char c = 'A';`     |
| boolean | 1 bit   | `boolean b = true;` |

### 🔹 Variables

```java
int age = 25;
String name = "Ganesh";
```

---

## 🔷 Type Casting

### 🔹 Implicit Casting (Widening):

```java
int i = 100;
long l = i;       // OK
```

### 🔹 Explicit Casting (Narrowing):

```java
double d = 9.7;
int i = (int) d;  // 9
```

---

## 🔷 Operators

### 🔹 Arithmetic:

```java
+, -, *, /, %
```

### 🔹 Logical:

```java
&& (AND), || (OR), ! (NOT)
```

### 🔹 Relational:

```java
==, !=, >, <, >=, <=
```

### 🔹 Assignment and Unary:

```java
=, +=, -=, ++, --
```

---

## 🔷 Control Statements

### 🔹 if-else

```java
if (age > 18) {
    System.out.println("Adult");
} else {
    System.out.println("Minor");
}
```

### 🔹 switch

```java
switch (day) {
    case 1: System.out.println("Monday"); break;
    default: System.out.println("Unknown");
}
```

### 🔹 Loops

```java
for (int i = 0; i < 5; i++) System.out.println(i);

int i = 0;
while (i < 5) { System.out.println(i++); }

int j = 0;
do { System.out.println(j++); } while (j < 5);
```

---

## 🔷 Input/Output in Java

### 🔹 Using `Scanner` (Most common)

```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);
System.out.print("Enter your name: ");
String name = sc.nextLine();
System.out.println("Welcome, " + name);
```

### 🔹 Using `BufferedReader`

```java
import java.io.*;

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
System.out.print("Enter age: ");
int age = Integer.parseInt(br.readLine());
```

---

## 🧠 Summary

| Topic         | Importance                       |
| ------------- | -------------------------------- |
| Java Overview | Understanding ecosystem          |
| JVM/JRE/JDK   | Core to running Java             |
| Hello World   | Foundation for all code          |
| Data Types    | Basics of variables and memory   |
| Type Casting  | Widening/narrowing conversions   |
| Operators     | Performing logic and computation |
| Control Flow  | Making decisions, loops          |
| Input/Output  | Reading user or file input       |

---

