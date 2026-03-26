package crudoperation2.controller;

import crudoperation2.entity.Employee;
import crudoperation2.repo.MyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MyController {
    @Autowired
    private MyRepo repo;
    // INSERT (Use JPA - SAFE)
    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody Employee emp) {
        repo.save(emp);   // IMPORTANT
        return "Employee Added";
    }
    //  GET ALL (Native)
    @GetMapping("/getAllEmployee")
    public List<Employee> getAll() {
        return repo.getAllEmployees();
    }
    // GET BY ID
    @GetMapping("/getEmployee/{id}")
    public Employee getById(@PathVariable int id) {
        return repo.getEmployeeById(id);
    }
    //  UPDATE
    @PutMapping("/updateEmployee/{id}")
    public String update(@PathVariable int id, @RequestBody Employee emp) {
        int rows = repo.updateEmployee(emp.getName(), emp.getSalary(), id);

        if (rows > 0)
            return "Updated Successfully";
        else
            return "Employee Not Found";
    }
    //  DELETE
    @DeleteMapping("/deleteEmployee/{id}")
    public String delete(@PathVariable int id) {
        int rows = repo.deleteEmployee(id);
        if (rows > 0)
            return "Deleted Successfully";
        else
            return "Employee Not Found";
    }
}

