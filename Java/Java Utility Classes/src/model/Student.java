package model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a student with name, roll number, and marks for 5 subjects.
 */
public class Student {
    private String name;
    private String rollNumber;
    private int[] marks; // 5 subjects

    public Student(String name, String rollNumber, int[] marks) {
        this.name = Objects.requireNonNull(name);
        this.rollNumber = Objects.requireNonNull(rollNumber);
        if (marks == null || marks.length != 5) {
            throw new IllegalArgumentException("Marks array must have 5 elements.");
        }
        this.marks = Arrays.copyOf(marks, 5);
    }

    public String getName() { return name; }
    public String getRollNumber() { return rollNumber; }
    public int[] getMarks() { return Arrays.copyOf(marks, 5); }

    public int getTotal() {
        int sum = 0;
        for (int m : marks) sum += m;
        return sum;
    }

    public double getAverage() {
        return getTotal() / 5.0;
    }

    public double getPercentage() {
        return (getTotal() / 500.0) * 100;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-10s %-20s %-5d %-7.2f %-7.2f",
                name, rollNumber, Arrays.toString(marks), getTotal(), getAverage(), getPercentage());
    }
}
