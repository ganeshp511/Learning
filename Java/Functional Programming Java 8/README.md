# Employee Analyzer

A Java 8 mini-project demonstrating functional programming concepts using a simple Employee analytics CLI.

## 🚀 Features
- Uses Java 8 functional interfaces: `Predicate`, `Function`, `Supplier`, `Consumer`
- Demonstrates Stream API for filtering, sorting, grouping, and aggregating
- Method references and lambda expressions
- Function composition with `andThen`/`compose`
- Console menu for interactive analysis
- Organized code: `model`, `service`, `util`, `main`, `test`

## 📦 Project Structure
```
src/
  model/Employee.java
  service/EmployeeService.java
  util/EmployeeDataSupplier.java
  main/Main.java
  test/EmployeeServiceTest.java (optional)
```

## 🧑‍💻 How to Run
1. **Compile:**
   - Open terminal in project root.
   - Run:
     ```
     javac -d out src/model/*.java src/service/*.java src/util/*.java src/main/*.java
     ```
2. **Run Main CLI:**
   - Run:
     ```
     java -cp out main.Main
     ```
3. **Run Tests (optional):**
   - Run:
     ```
     javac -d out -cp out src/test/EmployeeServiceTest.java
     java -cp out test.EmployeeServiceTest
     ```

## 📝 Functional Requirements Demonstrated
- Filter employees by department (`Predicate`)
- Top 3 highest paid employees (`Comparator`)
- Group by department (`Collectors.groupingBy`)
- Average salary per department (`Collectors.averagingDouble`)
- Print employee reports (`Consumer`)
- Lazy creation of test data (`Supplier`)
- Map/transform employee data (`Function`)
- Function composition (`Function.andThen`)
- Method references for clean syntax

## 📚 Example Menu
```
==== Employee Analyzer Menu ====
1. Filter by Department
2. Top 3 Highest Paid
3. Group by Department
4. Average Salary by Department
5. Print Employee Report
6. Show Name & Department
7. Show Composed Name Transform
0. Exit
```

## 🛠️ Requirements
- Java 8+
- No external libraries

---
MIT License
