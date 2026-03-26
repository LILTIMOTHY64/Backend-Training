package crudoperation.controller;

import crudoperation.entity.Employee;
import crudoperation.repo.MyRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MyController {

    private final MyRepo repository;

    public MyController(MyRepo repository) {
        this.repository = repository;
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @GetMapping("/getEmployeeById/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> emp = repository.findById(id);

        return emp.orElse(null);
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody Employee employee) {
        repository.save(employee);
        return "Employee added successfully";
    }

    @DeleteMapping("/deleteEmployeeById/{id}")
    public String deleteEmployeeById(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Employee deleted successfully";
        }
        return "Employee Not Found";
    }

    @DeleteMapping("/deleteAllEmployees")
    public String deleteAllEmployees() {
        repository.deleteAll();
        return "All Employees deleted successfully";
    }

    @PutMapping("/updateEmployeeById/{id}")
    public String updateEmployeeById(@PathVariable Integer id, @RequestBody Employee employee) {
        Optional<Employee> emp = repository.findById(id);
        if (emp.isPresent()) {
            Employee existingEmployee = emp.get();
            existingEmployee.setName(employee.getName());
            existingEmployee.setSalary(employee.getSalary());
            repository.save(existingEmployee);
            return "Employee updated successfully";
        }
        return "Employee Not Found";
    }
}
