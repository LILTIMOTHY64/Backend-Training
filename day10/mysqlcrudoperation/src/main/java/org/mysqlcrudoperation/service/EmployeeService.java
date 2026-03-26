package org.mysqlcrudoperation.service;

import org.mysqlcrudoperation.entity.Employee;
import org.mysqlcrudoperation.repo.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {


    private final EmployeeRepo repository;

    public EmployeeService(EmployeeRepo repository) {
        this.repository = repository;
    }

    //GET ALL EMPLOYEES
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    //GET EMPLOYEE BY ID
    public Employee getEmployeeById(int id) {
        return repository.findById(id).orElse(null);
    }

    //ADD EMPLOYEE
    public String addEmployee(Employee employee) {
        repository.save(employee);
        return "Employee added successfully";
    }

    //UPDATE EMPLOYEE
    public String updateEmployee(Integer id, Employee employee) {
        Employee existingEmployee = repository.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setSalary(employee.getSalary());
            repository.save(existingEmployee);
        } else {
            return "Employee not found";
        }
        return "Employee updated successfully";
    }

    //DELETE EMPLOYEE
    public String deleteEmployee(int id) {
        repository.deleteById(id);
        return "Employee deleted successfully";
    }

    //DELETE ALL EMPLOYEES
    public String deleteAllEmployees() {
        repository.deleteAllEmployees();
        return "All employees deleted successfully";
    }
}
