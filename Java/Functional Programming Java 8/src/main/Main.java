package main;

import model.Employee;
import service.EmployeeService;
import util.EmployeeDataSupplier;

import java.util.*;

/**
 * Main class for Employee Analyzer CLI
 */
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeDataSupplier.createTestData();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            EmployeeService.printMenu();
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter department: ");
                    String dept = scanner.nextLine();
                    List<Employee> filtered = EmployeeService.filterByDepartment(employees, dept);
                    System.out.println("Filtered Employees:");
                    EmployeeService.printEmployeeReport(filtered);
                    break;
                case 2:
                    System.out.println("Top 3 Highest Paid Employees:");
                    EmployeeService.printEmployeeReport(EmployeeService.top3HighestPaid(employees));
                    break;
                case 3:
                    System.out.println("Employees Grouped by Department:");
                    Map<String, List<Employee>> grouped = EmployeeService.groupByDepartment(employees);
                    grouped.forEach((d, emps) -> {
                        System.out.println("Department: " + d);
                        EmployeeService.printEmployeeReport(emps);
                    });
                    break;
                case 4:
                    System.out.println("Average Salary by Department:");
                    Map<String, Double> avg = EmployeeService.averageSalaryByDepartment(employees);
                    avg.forEach((d, a) -> System.out.printf("%s: %.2f\n", d, a));
                    break;
                case 5:
                    System.out.println("All Employees:");
                    EmployeeService.printEmployeeReport(employees);
                    break;
                case 6:
                    System.out.println("Employee Name & Department:");
                    EmployeeService.employeeNameAndDept(employees).forEach(System.out::println);
                    break;
                case 7:
                    System.out.println("Composed Name Transform (toUpperCase):");
                    EmployeeService.composedNameTransform(employees).forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 0);
        scanner.close();
    }
}
