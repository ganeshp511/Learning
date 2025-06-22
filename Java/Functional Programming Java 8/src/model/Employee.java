package model;

import java.time.LocalDate;

/**
 * Employee POJO for Employee Analyzer project.
 */
public class Employee {
    private int id;
    private String name;
    private double salary;
    private String department;
    private LocalDate joiningDate;

    public Employee(int id, String name, double salary, String department, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.joiningDate = joiningDate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public LocalDate getJoiningDate() { return joiningDate; }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s', salary=%.2f, department='%s', joiningDate=%s}",
                id, name, salary, department, joiningDate);
    }
}
