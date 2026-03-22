package org.webbasedapplication.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.webbasedapplication.Entity.Employee;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();

    // 1. Create Employee (POST)
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee emp) {
        // Name Validation: Max 2 spaces
        int spaceCount = emp.getName().length() - emp.getName().replace(" ", "").length();
        if (spaceCount > 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Name: Max 2 spaces allowed.");
        }

        // Age Validation: 19-60
        if (emp.getAge() < 19 || emp.getAge() > 60) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Age: Must be between 19 and 60.");
        }

        // Designation & Salary Logic
        if (emp.getDesignation().equalsIgnoreCase("Programmer")) {
            emp.setSalary(20000);
        } else if (emp.getDesignation().equalsIgnoreCase("Tester")) {
            emp.setSalary(15000);
        } else if (emp.getDesignation().equalsIgnoreCase("Manager")) {
            emp.setSalary(50000);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Designation Entered.");
        }

        employees.add(emp);
        return emp;
    }

    // 2. Display All Employees (GET)
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // 3. Raise Salary (PUT)
    @PutMapping("/raise")
    public String raiseSalary(@RequestParam String name, @RequestParam int percent) {
        if (percent < 1 || percent > 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Max Allowed Raise is 10%.");
        }

        for (Employee e : employees) {
            if (e.getName().equalsIgnoreCase(name)) {
                int increase = (int) (e.getSalary() * 0.01 * percent);
                e.setSalary(e.getSalary() + increase);
                return "Salary updated for " + name + ". New Salary: " + e.getSalary();
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found.");
    }

    // 4. Delete Employee (DELETE)
    @DeleteMapping("/employees")
    public String deleteEmployee(@RequestParam String name) {
        for (Employee e : employees) {
            if (e.getName().equalsIgnoreCase(name)) {
                employees.remove(e);
                return "Employee " + name + " deleted successfully.";
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found.");
    }
}
