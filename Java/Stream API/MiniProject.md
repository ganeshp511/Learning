Great idea! Let's build a **mini Java project** that integrates:

* ✅ **Generics**
* ✅ **Exception Handling**
* ✅ **Stream API**

We’ll build a **"Student Performance Analyzer"** – a modular, real-world app to:

* Store students with subjects and scores
* Handle invalid data using custom exceptions
* Analyze student performance using streams
* Use generics to build reusable repository/utility code

---

## 🔧 Project: **Student Performance Analyzer**

---

### ✅ **Project Structure**

```
StudentPerformanceAnalyzer/
│
├── model/
│   └── Student.java
│   └── SubjectScore.java
│
├── exception/
│   └── InvalidScoreException.java
│   └── StudentNotFoundException.java
│
├── repository/
│   └── GenericRepository<T>
│   └── StudentRepository.java
│
├── service/
│   └── StudentService.java
│
├── util/
│   └── AnalyticsUtils.java
│
└── Main.java
```

---

## 1. ✅ `model/Student.java` and `SubjectScore.java`

```java
public class SubjectScore {
    private String subject;
    private double score;

    public SubjectScore(String subject, double score) {
        this.subject = subject;
        this.score = score;
    }

    public String getSubject() { return subject; }
    public double getScore() { return score; }
}
```

```java
import java.util.List;

public class Student {
    private String id;
    private String name;
    private List<SubjectScore> scores;

    public Student(String id, String name, List<SubjectScore> scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<SubjectScore> getScores() { return scores; }
}
```

---

## 2. ✅ `exception/InvalidScoreException.java` and `StudentNotFoundException.java`

```java
public class InvalidScoreException extends RuntimeException {
    public InvalidScoreException(String message) {
        super(message);
    }
}
```

```java
public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
```

---

## 3. ✅ `repository/GenericRepository<T>`

```java
import java.util.*;

public class GenericRepository<T> {
    private Map<String, T> storage = new HashMap<>();

    public void save(String id, T entity) {
        storage.put(id, entity);
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }
}
```

---

## 4. ✅ `repository/StudentRepository.java`

```java
public class StudentRepository extends GenericRepository<Student> {
    // Custom methods can be added here
}
```

---

## 5. ✅ `service/StudentService.java`

```java
import java.util.List;
import java.util.Optional;

public class StudentService {
    private StudentRepository repository = new StudentRepository();

    public void addStudent(Student student) {
        for (SubjectScore score : student.getScores()) {
            if (score.getScore() < 0 || score.getScore() > 100) {
                throw new InvalidScoreException("Score out of range: " + score.getScore());
            }
        }
        repository.save(student.getId(), student);
    }

    public Student getStudent(String id) throws StudentNotFoundException {
        return repository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException("Student not found: " + id));
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }
}
```

---

## 6. ✅ `util/AnalyticsUtils.java`

```java
import java.util.*;
import java.util.stream.*;

public class AnalyticsUtils {
    public static OptionalDouble getAverageScore(Student student) {
        return student.getScores().stream()
            .mapToDouble(SubjectScore::getScore)
            .average();
    }

    public static Map<String, Double> getSubjectWiseAverage(List<Student> students) {
        return students.stream()
            .flatMap(s -> s.getScores().stream())
            .collect(Collectors.groupingBy(
                SubjectScore::getSubject,
                Collectors.averagingDouble(SubjectScore::getScore)
            ));
    }

    public static List<String> getTopPerformers(List<Student> students, double minAverage) {
        return students.stream()
            .filter(s -> getAverageScore(s).orElse(0) >= minAverage)
            .map(Student::getName)
            .collect(Collectors.toList());
    }
}
```

---

## 7. ✅ `Main.java`

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();

        try {
            service.addStudent(new Student("S1", "Alice", List.of(
                new SubjectScore("Math", 85),
                new SubjectScore("Science", 92)
            )));

            service.addStudent(new Student("S2", "Bob", List.of(
                new SubjectScore("Math", 70),
                new SubjectScore("Science", 75)
            )));

            service.addStudent(new Student("S3", "Charlie", List.of(
                new SubjectScore("Math", 95),
                new SubjectScore("Science", 100)
            )));

        } catch (InvalidScoreException e) {
            System.err.println("Invalid score: " + e.getMessage());
        }

        try {
            Student student = service.getStudent("S1");
            System.out.println("Average of " + student.getName() + ": " +
                AnalyticsUtils.getAverageScore(student).orElse(0));

        } catch (StudentNotFoundException e) {
            System.err.println(e.getMessage());
        }

        List<Student> all = service.getAllStudents();
        System.out.println("Top performers (>85): " + AnalyticsUtils.getTopPerformers(all, 85));
        System.out.println("Subject-wise Averages: " + AnalyticsUtils.getSubjectWiseAverage(all));
    }
}
```

---

## 🔍 Concepts Covered:

| Concept                | Where Used                                                                 |
| ---------------------- | -------------------------------------------------------------------------- |
| ✅ Generics             | `GenericRepository<T>`, reusable repo logic                                |
| ✅ Exception Handling   | `InvalidScoreException`, `StudentNotFoundException`, `try/catch`, `throws` |
| ✅ Stream API           | `AnalyticsUtils` methods: `filter`, `map`, `average`, `groupingBy`         |
| ✅ Optional & Stream    | `OptionalDouble`, `Optional<T>`, `.orElseThrow()`                          |
| ✅ Functional Pipelines | Clean, chainable `map`, `filter`, `collect` calls                          |

---

Here’s a complete, professional `README.md` file for your **Student Performance Analyzer** project:

---

```markdown
# 🎓 Student Performance Analyzer

A mini Java project that demonstrates the powerful combination of **Generics**, **Exception Handling**, and **Stream API** by analyzing student academic performance.

---

## 🚀 Features

- Add and manage students with multiple subject scores
- Validate scores with **custom exceptions**
- Perform real-time analysis using **Stream API**
- Use of **Generic Repository Pattern**
- Clean and modular code structure
- Ready to extend into a Spring Boot API

---

## 🧠 Concepts Covered

| Concept                | Description                                               |
|------------------------|-----------------------------------------------------------|
| ✅ Generics            | Reusable repository and utility methods                    |
| ✅ Exception Handling  | Custom checked/unchecked exceptions with try-catch logic   |
| ✅ Stream API          | Functional pipelines: map, filter, reduce, groupingBy      |
| ✅ Optional            | Safe null-free processing of stream results                |
| ✅ Functional Patterns | Pure functions, immutability, stateless operations         |

---

## 📁 Project Structure

```

StudentPerformanceAnalyzer/
├── model/
│   ├── Student.java
│   └── SubjectScore.java
│
├── exception/
│   ├── InvalidScoreException.java
│   └── StudentNotFoundException.java
│
├── repository/
│   ├── GenericRepository.java
│   └── StudentRepository.java
│
├── service/
│   └── StudentService.java
│
├── util/
│   └── AnalyticsUtils.java
│
└── Main.java

````

---

## 📦 How to Run

### ✅ Requirements:
- Java 11+ installed
- Any IDE (IntelliJ, VS Code, Eclipse)
- Or use command-line `javac` and `java`

### ▶️ Run the App

1. Clone or download this repo
2. Open in your IDE and run `Main.java`

OR

```bash
javac Main.java
java Main
````

---

## 🧪 Sample Output

```
Average of Alice: 88.5
Top performers (>85): [Alice, Charlie]
Subject-wise Averages: {Math=83.33333333333333, Science=89.0}
```

---

## 🛠️ Future Enhancements

* 🔄 Convert to Spring Boot REST API
* 💾 Add file or database persistence
* 📈 Export analytics to CSV or PDF
* 🧪 Add unit tests using JUnit

---

## 📚 Learning Objectives

This project is designed to help you:

* Understand **real-world uses** of Java Generics
* Apply **robust exception handling** patterns
* Master **Stream API operations** for data processing
* Build reusable, testable, maintainable code

---

## 🙌 Credits

Built with ❤️ by \[Your Name] as a practice project to master Java fundamentals.

---

## 📄 License

This project is licensed under the MIT License.

```

---

