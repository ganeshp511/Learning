ganeshp511: provide long details on: 5. Java Methods and Recursion
Method creation, overloading

Pass by value vs pass by reference


GitHub Copilot: Here are detailed explanations for the requested topics on Java Methods and Recursion:

---

### 5. Java Methods and Recursion

#### Method Creation

A method in Java is a block of code that performs a specific task, defined within a class. Methods help organize code, promote reusability, and improve readability.

**Syntax:**
```java
returnType methodName(parameter1Type parameter1, parameter2Type parameter2, ...) {
    // method body
    // optional return statement
}
```
**Example:**
```java
public int add(int a, int b) {
    return a + b;
}
```
- `public`: Access modifier
- `int`: Return type
- `add`: Method name
- `(int a, int b)`: Parameters
- `return a + b;`: Returns the sum

**Calling a Method:**
```java
int result = add(5, 3); // result is 8
```

#### Method Overloading

Method overloading allows multiple methods in the same class to have the same name but different parameter lists (type, number, or order of parameters). The correct method is chosen at compile time based on the arguments.

**Example:**
```java
public int multiply(int a, int b) {
    return a * b;
}

public double multiply(double a, double b) {
    return a * b;
}

public int multiply(int a, int b, int c) {
    return a * b * c;
}
```
- All methods are named `multiply` but differ in parameter types or count.

#### Pass by Value vs Pass by Reference

**Java is strictly pass by value.** However, the behavior differs for primitive types and objects:

- **Primitive Types:** The method receives a copy of the value. Changes inside the method do not affect the original variable.
- **Objects:** The method receives a copy of the reference (address) to the object. Changes to the object's fields inside the method affect the original object, but reassigning the reference does not affect the original reference.

**Example with Primitive:**
```java
void changeValue(int x) {
    x = 10;
}

int a = 5;
changeValue(a);
// a is still 5
```

**Example with Object:**
```java
void changeName(Person p) {
    p.name = "Alice";
}

Person person = new Person("Bob");
changeName(person);
// person.name is now "Alice"
```
But:
```java
void reassign(Person p) {
    p = new Person("Charlie");
}

reassign(person);
// person still refers to the original object, not "Charlie"
```

#### Recursion

Recursion is when a method calls itself to solve a problem by breaking it down into smaller subproblems.

**Example: Factorial**
```java
int factorial(int n) {
    if (n == 0) return 1;
    return n * factorial(n - 1);
}
```
- **Base case:** Stops recursion (`n == 0`)
- **Recursive case:** Calls itself with a smaller value

**Other Examples:** Fibonacci sequence, reversing a string, traversing data structures (trees, graphs).

---
