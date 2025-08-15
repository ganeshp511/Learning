To create a `.jar` file from a Java project in the **latest version of IntelliJ IDEA (2024.1 or newer)**, follow the detailed **step-by-step** guide below, covering **both IntelliJ GUI** and **command-line** (using `javac` and `jar`) approaches.

---

## ✅ Part 1: Setup & Build `.jar` Using IntelliJ IDEA (GUI)

### 🔧 Step 1: Create or Open a Java Project

1. Open IntelliJ IDEA.
2. **Create New Project**:

   * Choose **Java**.
   * Select **JDK** (Install if needed).
   * Click **Next** and finish setup.

   *or*

   **Open existing project**:

   * `File > Open` and select the project folder.

---

### ⚙️ Step 2: Configure Project SDK

1. Go to `File > Project Structure` or press `Ctrl+Alt+Shift+S`.
2. In **Project** tab:

   * Set **Project SDK** to a valid Java version (e.g., Java 17 or Java 21).
3. Click **Apply**.

---

### 📦 Step 3: Create Artifact for JAR

1. Go to `File > Project Structure > Artifacts`.
2. Click **+ > JAR > From modules with dependencies**.
3. Choose your **main class** (entry point) if your app has one.
4. In output layout:

   * IntelliJ adds `*.class` files automatically.
   * You can include resource files (e.g., `config.properties`) if needed.
5. Set the **Output Directory** (e.g., `out/artifacts/myjar`).
6. Click **Apply** and **OK**.

---

### 🛠️ Step 4: Build the JAR

1. Go to `Build > Build Artifacts`.
2. Select your artifact: e.g., `myjar:jar`.
3. Click **Build**.

➡️ Your JAR file will be located in:

```
<project-root>/out/artifacts/myjar/myjar.jar
```

You can now run it with:

```bash
java -jar out/artifacts/myjar/myjar.jar
```

---

## 🧪 Part 2: Alternative — Command Line Approach (Manual Compile & JAR)

This works without any IDE. You only need JDK installed and environment variable `JAVA_HOME` set.

### 📁 Folder structure example:

```
MyProject/
├── src/
│   └── Main.java
├── out/
```

---

### 🧾 Step 1: Compile `.java` Files

```bash
javac -d out src/Main.java
```

Explanation:

* `-d out` → put `.class` files in the `out` folder.

---

### 🧰 Step 2: Create Manifest File (optional but recommended)

Create a file named `manifest.txt`:

```
Main-Class: Main
```

*(Leave a newline at the end)*

---

### 📦 Step 3: Package into JAR

```bash
jar cfm MyApp.jar manifest.txt -C out .
```

Explanation:

* `c` → create new JAR
* `f` → specify filename
* `m` → include manifest
* `-C out .` → change to `out` dir and include all compiled files

➡️ Result: `MyApp.jar` will be created in your project folder.

---

### ▶️ Step 4: Run the JAR

```bash
java -jar MyApp.jar
```

---

## ✅ Summary

| Action        | IntelliJ GUI Approach                         | Command-Line Approach                     |
| ------------- | --------------------------------------------- | ----------------------------------------- |
| Setup Project | File > New Project                            | Create `src/` and code manually           |
| Compile Code  | Automatic by IntelliJ                         | `javac -d out src/Main.java`              |
| Package JAR   | File > Project Structure > Artifacts > Build  | `jar cfm MyApp.jar manifest.txt -C out .` |
| Run           | `java -jar out/artifacts/yourjar/yourjar.jar` | `java -jar MyApp.jar`                     |

---

