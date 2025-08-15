To **add an external JAR file** to your **Java project in the latest IntelliJ IDEA (2024.1 or newer)**, follow one of the methods below. IntelliJ has streamlined the process — here's the **step-by-step guide** for all relevant scenarios:

---

## ✅ Method 1: Add JAR via Project Structure (GUI) — Best for Small Projects

### 🔧 Step-by-Step

1. **Open IntelliJ IDEA** and load your project.
2. Go to **`File > Project Structure`** (or press `Ctrl + Alt + Shift + S`).
3. Select **`Modules`** on the left.
4. Select your module (usually the main one listed).
5. Go to the **`Dependencies`** tab.
6. Click the **`+`** (top right) > Choose **`JARs or directories`**.
7. Select your external `.jar` file (e.g., `lib/mylibrary.jar`).
8. Set **Scope** to `Compile` (default).
9. Click **OK > Apply**.

### ✅ Result:

The JAR is now added to your project classpath, and you can import and use its classes.

---

## ✅ Method 2: Drag & Drop JAR File into Project

1. Drag the `.jar` file into your IntelliJ project’s `lib/` or root folder.
2. IntelliJ may **auto-detect** and prompt to add it to module dependencies.

   * If not, follow **Step 3–9** from **Method 1** above to add it manually.

---

## ✅ Method 3: Use `pom.xml` (Maven) or `build.gradle` (Gradle)

If you're using **Maven** or **Gradle**, it's better to manage dependencies there (cleaner, portable):

### Maven – Add to `pom.xml`:

```xml
<dependency>
    <groupId>org.example</groupId>
    <artifactId>mylib</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/lib/mylibrary.jar</systemPath>
</dependency>
```

> ⚠️ Use `system` scope only if the JAR is **not available in Maven Central**. Otherwise, download it properly via repository.

---

### Gradle – Add to `build.gradle`:

```groovy
dependencies {
    implementation files('lib/mylibrary.jar')
}
```

---

## ✅ Method 4: Set CLASSPATH (not recommended in IDE)

You can use `-cp` or `CLASSPATH` manually, but it’s **not recommended inside IntelliJ** because IntelliJ manages its own build path.

---

## 📦 Best Practice

| Project Type      | Recommended Method              |
| ----------------- | ------------------------------- |
| Simple/Manual     | Project Structure > Add JAR     |
| Maven project     | Add dependency to `pom.xml`     |
| Gradle project    | Add `files()` in `build.gradle` |
| Multi-module/Team | Use Maven/Gradle always         |

---

