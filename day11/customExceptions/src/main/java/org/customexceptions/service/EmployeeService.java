package org.customexceptions.service;

import org.customexceptions.entity.Employee;
import org.customexceptions.exceptions.ResourceNotFoundException;
import org.customexceptions.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    // Get All
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // Get By ID
    public Employee getEmployeeById(int id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    // Add Employee
    public String addEmployee(Employee employee) {
        repository.save(employee);
        return "Employee Added Successfully";
    }

    // Update Employee
    public String updateEmployee(Employee employee, Integer id) {
        Employee existingEmployee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        repository.save(existingEmployee);
        return "Employee Updated Successfully";
    }

    // Delete Employee
    public String deleteEmployee(Integer id) {

        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        repository.deleteById(id);
        return "Employee Deleted Successfully";

    }

    // Delete All Employees
    public String deleteAllEmployees(){
        repository.deleteAll();
        return "All Employees Deleted Successfully";
    }
}
