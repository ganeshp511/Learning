package service;

import model.Employee;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Provides functional operations on Employee data.
 */
public class EmployeeService {
    // Filter employees by department using Predicate
    public static List<Employee> filterByDepartment(List<Employee> employees, String department) {
        Predicate<Employee> byDept = e -> e.getDepartment().equalsIgnoreCase(department);
        return employees.stream().filter(byDept).collect(Collectors.toList());
    }

    // Get top 3 highest paid employees using Comparator
    public static List<Employee> top3HighestPaid(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    // Group employees by department
    public static Map<String, List<Employee>> groupByDepartment(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }

    // Calculate average salary per department
    public static Map<String, Double> averageSalaryByDepartment(List<Employee> employees) {
        return employees.stream().collect(
                Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary))
        );
    }

    // Print employee report using Consumer
    public static void printEmployeeReport(List<Employee> employees) {
        Consumer<Employee> printer = System.out::println;
        employees.forEach(printer);
    }

    // Map and transform employee data using Function
    public static List<String> employeeNameAndDept(List<Employee> employees) {
        Function<Employee, String> nameDept = e -> e.getName() + " (" + e.getDepartment() + ")";
        return employees.stream().map(nameDept).collect(Collectors.toList());
    }

    // Demonstrate function composition
    public static List<String> composedNameTransform(List<Employee> employees) {
        Function<Employee, String> name = Employee::getName;
        Function<String, String> toUpper = String::toUpperCase;
        Function<Employee, String> composed = name.andThen(toUpper);
        return employees.stream().map(composed).collect(Collectors.toList());
    }

    // Static utility method example
    public static void printMenu() {
        System.out.println("\n==== Employee Analyzer Menu ====");
        System.out.println("1. Filter by Department");
        System.out.println("2. Top 3 Highest Paid");
        System.out.println("3. Group by Department");
        System.out.println("4. Average Salary by Department");
        System.out.println("5. Print Employee Report");
        System.out.println("6. Show Name & Department");
        System.out.println("7. Show Composed Name Transform");
        System.out.println("0. Exit");
    }
}
