package util;

import model.Employee;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Supplies test employee data using Supplier functional interface.
 */
public class EmployeeDataSupplier implements Supplier<List<Employee>> {
    @Override
    public List<Employee> get() {
        return Arrays.asList(
            new Employee(1, "Alice", 90000, "Engineering", LocalDate.of(2018, 1, 10)),
            new Employee(2, "Bob", 85000, "Engineering", LocalDate.of(2019, 3, 15)),
            new Employee(3, "Charlie", 120000, "HR", LocalDate.of(2017, 7, 23)),
            new Employee(4, "Diana", 95000, "HR", LocalDate.of(2020, 2, 5)),
            new Employee(5, "Eve", 110000, "Sales", LocalDate.of(2016, 11, 30)),
            new Employee(6, "Frank", 105000, "Sales", LocalDate.of(2018, 5, 18)),
            new Employee(7, "Grace", 99000, "Engineering", LocalDate.of(2021, 6, 1)),
            new Employee(8, "Heidi", 98000, "Sales", LocalDate.of(2019, 9, 12))
        );
    }

    // Static method for convenience
    public static List<Employee> createTestData() {
        return new EmployeeDataSupplier().get();
    }
}
