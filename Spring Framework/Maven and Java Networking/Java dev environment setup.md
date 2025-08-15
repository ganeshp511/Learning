## 🧑‍💻 1. Configure IntelliJ for a **Single Java File** (Quick Script or Test Program)

> Best for: Trying out simple programs, algorithms, coding exercises.

---

### ✅ Steps:

1. **Open IntelliJ IDEA**.
2. Click **New Project**.
3. Choose **Java** (on the left).
4. **Uncheck** “Create project from template”.
5. Click **Next**.
6. Name the project (e.g., `HelloWorldSingle`).
7. Click **Finish**.

---

### Add Single Java File:

1. Right-click `src` > **New > Java Class**.
2. Name it `Main`.
3. Write your code:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

---

### Run:

* Click the **green triangle** next to `main()` or the filename.
* Output appears in the **Run** window.

---

📝 **Note**: No `project structure`, dependencies, or build config needed. Ideal for quick experiments.

---

## 📁 2. Configure IntelliJ for a **Standard Java Project (Non-Maven)**

> Best for: Small Java apps or learning projects **without external dependencies**.

---

### ✅ Steps:

1. **Open IntelliJ IDEA > New Project**.
2. Choose **Java**.
3. Uncheck templates, click **Next**.
4. Give your project a name: `MyJavaProject`.
5. Choose a **JDK** (Install or select JDK 17 or 21).
6. Click **Finish**.

---

### Project Structure:

```
MyJavaProject/
├── src/
│   └── Main.java
```

---

### Add Java Class:

1. Right-click `src` > **New > Java Class**.
2. Name: `Main`.
3. Add code as usual.

---

### Optional: Add External JARs

1. Go to `File > Project Structure > Modules > Dependencies`.
2. Click `+` > "JARs or directories", and select your `.jar` file.
3. Set scope to **Compile**.

---

### Compile & Run:

* IntelliJ automatically compiles on save.
* Use the **green run button** or right-click class > Run.

---

🧠 Tip: For more automation (e.g., `.jar` build), consider converting to Maven or Gradle.

---

## 📦 3. Configure IntelliJ for a **Java Maven Project**

> Best for: Enterprise projects, dependency management, multi-module builds.

---

### ✅ Steps:

1. **Open IntelliJ IDEA > New Project**.
2. Choose **Maven** on the left.
3. **Check “Create from archetype”** (or leave it unchecked for a minimal setup).
4. Click **Next**.

---

### Configure Maven Info:

* **GroupId**: `com.example`
* **ArtifactId**: `myapp`
* **Version**: leave default (`1.0-SNAPSHOT`)
* Click **Next**.

---

### Project Structure:

```
myapp/
├── pom.xml
├── src/
│   ├── main/java/com/example/Main.java
│   └── test/java/
```

---

### Write Main Class:

1. Create a package: `com.example`
2. Add `Main.java`:

```java
package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Maven-based project running!");
    }
}
```

---

### Add Maven Dependency:

1. Open `pom.xml`.
2. Add:

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.13.0</version>
    </dependency>
</dependencies>
```

3. IntelliJ will auto-import it.

---

### Build & Run:

* Use the **Maven panel (right sidebar)** > `Lifecycle > package`
* Or right-click `pom.xml` > **Add as Maven Project**, then Run `Main`.

---

## 📊 Comparison Summary

| Setup Type            | Recommended For              | Dependency Support | Build Automation |
| --------------------- | ---------------------------- | ------------------ | ---------------- |
| Single Java File      | Quick tests, one-off scripts | ❌ Manual           | ❌ None           |
| Java Project (Manual) | Small apps, learners         | ✅ (Manual JARs)    | ⚠️ Manual        |
| Maven Project         | Enterprise, multi-module     | ✅ Automatic        | ✅ Built-in       |

---
