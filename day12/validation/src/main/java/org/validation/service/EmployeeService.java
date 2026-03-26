package org.validation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.validation.entity.Employee;
import org.validation.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

     public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee employee) {
        Employee existingEmployee = repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        return repository.save(existingEmployee);
    }

        public void deleteEmployee(Integer id) {
            repository.deleteById(id);
        }
}
