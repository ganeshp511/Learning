import model.Student;
import utils.StatisticsUtil;
import utils.DataLoader;

import java.util.*;
import java.util.Formatter;
import java.util.Optional;

/**
 * Main class for Student Performance Analyzer
 */
public class StudentPerformanceAnalyzer {
    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n==== Student Performance Analyzer ====");
            System.out.println("1. Add Student Info");
            System.out.println("2. Display All Students");
            System.out.println("3. Compute Statistics & Rank");
            System.out.println("4. Search Student");
            System.out.println("5. Random Bonus Generator");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int opt = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (opt) {
                case 1:
                    loader.addStudent();
                    break;
                case 2:
                    displayStudents(loader.getStudents());
                    break;
                case 3:
                    computeStats(loader.getStudents());
                    break;
                case 4:
                    System.out.print("Enter name or roll number to search: ");
                    String query = sc.nextLine();
                    Optional<Student> found = loader.searchStudent(query);
                    found.ifPresentOrElse(
                        s -> System.out.println("Found: " + s),
                        () -> System.out.println("Student not found.")
                    );
                    break;
                case 5:
                    System.out.print("Enter bonus points: ");
                    int bonus = sc.nextInt();
                    System.out.print("How many students to assign bonus? ");
                    int n = sc.nextInt();
                    StatisticsUtil.assignRandomBonus(loader.getStudents(), bonus, n);
                    System.out.println("Bonus assigned!");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Display students in tabular format using Formatter
    private static void displayStudents(List<Student> students) {
        Formatter fmt = new Formatter();
        fmt.format("%-15s %-10s %-20s %-5s %-7s %-7s\n", "Name", "RollNo", "Marks", "Tot", "Avg", "%");
        for (Student s : students) {
            fmt.format("%s\n", s);
        }
        System.out.println(fmt);
    }

    // Compute and display statistics, then rank students
    private static void computeStats(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students to analyze.");
            return;
        }
        StatisticsUtil.rankStudents(students);
        System.out.println("\nRanked Students:");
        displayStudents(students);
        Student top = students.get(0);
        System.out.printf("Topper: %s (%s) with %d marks\n", top.getName(), top.getRollNumber(), top.getTotal());
    }
}
