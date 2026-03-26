package org.mysqlcrudoperation.controller;

import org.mysqlcrudoperation.entity.Employee;
import org.mysqlcrudoperation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/getEmployeeById/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return service.getEmployeeById(id);
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody Employee employee) {
        return service.addEmployee(employee);
    }

    @PutMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable Integer id,
                                 @RequestBody Employee employee) {
        return service.updateEmployee(id, employee);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return service.deleteEmployee(id);
    }

    @DeleteMapping("/deleteAllEmployees")
    public String deleteAllEmployees() {
        return service.deleteAllEmployees();
    }
}
