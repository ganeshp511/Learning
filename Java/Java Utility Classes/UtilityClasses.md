Absolutely! Let’s go deep into **Java Utility Classes** — a group of helper classes that make it easier to perform common operations such as math, collection manipulation, formatting, and parsing.

---

## ✅ **Java Utility Classes – Deep Dive**

---

### 🧮 **1. Math and Random**

#### 🔹 `java.lang.Math`

The `Math` class provides methods for **mathematical operations** like `sin`, `cos`, `sqrt`, `pow`, etc. All methods are `static`.

**Key Methods:**

```java
Math.abs(-10);         // 10
Math.max(4, 9);        // 9
Math.min(3, 5);        // 3
Math.pow(2, 3);        // 8.0
Math.sqrt(16);         // 4.0
Math.round(3.6);       // 4
Math.floor(4.9);       // 4.0
Math.ceil(4.1);        // 5.0
Math.random();         // Returns double between 0.0 and 1.0
```

#### 🔹 `java.util.Random`

Use when you need more control over randomness (like ranges or seed values).

**Example:**

```java
Random rand = new Random();
int num = rand.nextInt(100);  // Random number between 0–99
double d = rand.nextDouble(); // Random double between 0.0–1.0
boolean b = rand.nextBoolean();
```

---

### 🔢 **2. Arrays, Collections, Objects**

#### 🔹 `java.util.Arrays`

Utility class for working with arrays.

**Examples:**

```java
int[] arr = {3, 1, 4, 2};
Arrays.sort(arr);               // Sorts array
Arrays.fill(arr, 0);            // Fills all elements with 0
Arrays.toString(arr);           // Converts to string
Arrays.binarySearch(arr, 2);    // Binary search (sorted array only)
```

#### 🔹 `java.util.Collections`

Used for **manipulating collections** like `List`, `Set`.

**Examples:**

```java
List<Integer> list = Arrays.asList(5, 2, 9);
Collections.sort(list);         // Ascending sort
Collections.reverse(list);      // Reverse order
Collections.max(list);          // Max element
Collections.shuffle(list);      // Random shuffle
Collections.unmodifiableList(list); // Make list read-only
```

#### 🔹 `java.util.Objects`

Null-safe helper methods.

**Examples:**

```java
Objects.equals(a, b);           // Null-safe equals
Objects.requireNonNull(obj);    // Throws NPE if obj is null
Objects.hash(a, b);             // Multi-field hashcode
Objects.toString(obj, "default"); // Null-safe toString
```

---

### 🔠 **3. StringTokenizer, Scanner, Formatter**

#### 🔹 `java.util.StringTokenizer` (Legacy)

Breaks strings into tokens based on delimiters.

**Example:**

```java
StringTokenizer st = new StringTokenizer("Apple,Banana,Mango", ",");
while (st.hasMoreTokens()) {
    System.out.println(st.nextToken());
}
```

🟡 Use **`String.split()` or `Scanner`** instead in modern code.

---

#### 🔹 `java.util.Scanner`

A modern and powerful utility for parsing **input from strings, files, or console**.

**Reading from console:**

```java
Scanner sc = new Scanner(System.in);
int a = sc.nextInt();
String s = sc.nextLine();
```

**Reading from a file:**

```java
Scanner sc = new Scanner(new File("data.txt"));
while (sc.hasNext()) {
    System.out.println(sc.next());
}
```

---

#### 🔹 `java.util.Formatter`

Used for **formatted output**, like `printf`.

**Example:**

```java
System.out.printf("Name: %s, Age: %d", "Ganesh", 28);
```

You can also write formatted output to strings/files:

```java
Formatter fmt = new Formatter();
fmt.format("Hex: %x", 255);  // Hex: ff
String output = fmt.toString();
```

---

### ❓ **4. Optional (Java 8)**

Helps **avoid `NullPointerException`** by representing values that may or may not exist.

#### 🔹 Creating Optional:

```java
Optional<String> name = Optional.of("Ganesh");           // Non-null
Optional<String> empty = Optional.empty();               // No value
Optional<String> maybeNull = Optional.ofNullable(null);  // Could be null
```

#### 🔹 Accessing Value:

```java
name.get();                        // Dangerous if empty
name.orElse("Default");            // Safe fallback
name.ifPresent(System.out::println); // Print only if present
```

#### 🔹 Functional Usage:

```java
name.map(String::toUpperCase)
    .filter(n -> n.startsWith("G"))
    .ifPresent(System.out::println);
```

**Use Case:** Safer return types from methods instead of returning nulls.

---

## 📌 Summary Table:

| Class             | Use Case                             |
| ----------------- | ------------------------------------ |
| `Math`            | Math operations                      |
| `Random`          | Random number generation             |
| `Arrays`          | Array sorting, searching             |
| `Collections`     | Collection utilities (sort, reverse) |
| `Objects`         | Null-safety methods                  |
| `StringTokenizer` | Tokenizing strings (legacy)          |
| `Scanner`         | Reading input from various sources   |
| `Formatter`       | Formatted output                     |
| `Optional`        | Null-safe wrappers for values        |

---

