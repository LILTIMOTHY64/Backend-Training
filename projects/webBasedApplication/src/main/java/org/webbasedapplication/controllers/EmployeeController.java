package org.webbasedapplication.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.webbasedapplication.entity.Employee;
import org.webbasedapplication.model.EmployeeCreateRequest;
import org.webbasedapplication.model.RaiseRequest;
import org.webbasedapplication.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 1. Create Employee (POST)
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody EmployeeCreateRequest emp) {
        return employeeService.createEmployee(emp);
    }

    // 2. Display All Employees (GET)
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // 3. Raise Salary (PUT)
    @PutMapping("/raise")
    public String raiseSalary(@Valid @RequestBody RaiseRequest request) {
        return employeeService.raiseSalary(request);
    }

    // 4. Delete Employee (DELETE)
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }
}
