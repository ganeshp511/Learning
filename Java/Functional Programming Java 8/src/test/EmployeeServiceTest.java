package test;

import model.Employee;
import service.EmployeeService;
import util.EmployeeDataSupplier;

import java.util.List;

public class EmployeeServiceTest {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeDataSupplier.createTestData();
        // Test: filterByDepartment
        List<Employee> eng = EmployeeService.filterByDepartment(employees, "Engineering");
        assert eng.size() == 3 : "Should be 3 engineers";
        // Test: composedNameTransform
        List<String> names = EmployeeService.composedNameTransform(employees);
        assert names.get(0).equals("ALICE") : "First name should be ALICE";
        System.out.println("All tests passed.");
    }
}
