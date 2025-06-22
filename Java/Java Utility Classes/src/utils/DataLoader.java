package utils;

import model.Student;
import java.util.*;
import java.util.Optional;
import java.util.StringTokenizer;

/**
 * Handles student data input, search, and management.
 */
public class DataLoader {
    private final List<Student> students = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    // Add a new student
    public void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter roll number: ");
        String roll = scanner.nextLine();
        int[] marks = new int[5];
        for (int i = 0; i < 5; i++) {
            System.out.printf("Enter mark for subject %d: ", i + 1);
            marks[i] = scanner.nextInt();
        }
        scanner.nextLine(); // consume newline
        students.add(new Student(name, roll, marks));
        System.out.println("Student added!\n");
    }

    // Get all students
    public List<Student> getStudents() {
        return students;
    }

    // Search by name or roll (using StringTokenizer for name)
    public Optional<Student> searchStudent(String query) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(query)) return Optional.of(s);
            StringTokenizer st = new StringTokenizer(s.getName());
            while (st.hasMoreTokens()) {
                if (st.nextToken().equalsIgnoreCase(query)) return Optional.of(s);
            }
        }
        return Optional.empty();
    }
}
