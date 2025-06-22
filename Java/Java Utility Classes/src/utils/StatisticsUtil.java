package utils;

import model.Student;
import java.util.*;

/**
 * Utility class for computing statistics and ranking students.
 */
public class StatisticsUtil {
    // Compute min, max, average for a student's marks
    public static int minMark(Student s) {
        return Arrays.stream(s.getMarks()).min().orElse(0);
    }

    public static int maxMark(Student s) {
        return Arrays.stream(s.getMarks()).max().orElse(0);
    }

    public static double averageMark(Student s) {
        return Arrays.stream(s.getMarks()).average().orElse(0);
    }

    // Sort students by total marks (descending)
    public static void rankStudents(List<Student> students) {
        students.sort((a, b) -> Integer.compare(b.getTotal(), a.getTotal()));
    }

    // Assign random bonus points to n students
    public static void assignRandomBonus(List<Student> students, int bonus, int n) {
        Collections.shuffle(students, new Random());
        for (int i = 0; i < Math.min(n, students.size()); i++) {
            int[] marks = students.get(i).getMarks();
            marks[0] = Math.min(marks[0] + bonus, 100); // Add bonus to first subject
        }
    }
}
