package org.webbasedapplication.service;

import org.springframework.stereotype.Service;
import org.webbasedapplication.entity.Employee;
import org.webbasedapplication.exceptions.ResourceNotFound;
import org.webbasedapplication.model.EmployeeCreateRequest;
import org.webbasedapplication.model.RaiseRequest;
import org.webbasedapplication.repository.EmployeeRepo;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    // 1. Display All Employees
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    // 2. Create Employee
    public Employee createEmployee(EmployeeCreateRequest employeeCreateRequest) {
        Employee emp = new Employee();
        emp.setName(employeeCreateRequest.getName());
        emp.setAge(employeeCreateRequest.getAge());
        emp.setDesignation(employeeCreateRequest.getDesignation());

        if (employeeCreateRequest.getDesignation().equals("Programmer")) {
            emp.setSalary(20000);
        } else if (employeeCreateRequest.getDesignation().equals("Tester")) {
            emp.setSalary(15000);
        } else {
            emp.setSalary(50000);
        }

        return employeeRepo.save(emp);
    }

    // 3. Raise Salary
    public String raiseSalary(RaiseRequest request) {

        Employee employee = employeeRepo.findByNameIgnoreCase(request.getName())
                .orElseThrow(() -> new ResourceNotFound("Employee not found."));

        int increase = (int) (employee.getSalary() * 0.01 * request.getPercent());
        employee.setSalary(employee.getSalary() + increase);
        employeeRepo.save(employee);

        return "Salary updated for " + request.getName() + ". New Salary: " + employee.getSalary();
    }

    // 4. Delete Employee
    public String deleteEmployee(Integer id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Employee not found."));

        employeeRepo.delete(employee);
        if (employeeRepo.count() == 0) {
            employeeRepo.resetAutoIncrement();
            return "Employee of ID " + id + " deleted successfully and auto-increment reset.";
        }
        return "Employee of ID " + id + " deleted successfully.";
    }
}
