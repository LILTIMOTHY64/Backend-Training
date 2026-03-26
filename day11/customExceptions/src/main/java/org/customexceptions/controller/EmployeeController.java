package org.customexceptions.controller;

import org.customexceptions.entity.Employee;
import org.customexceptions.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee(){
        return service.getAllEmployees();
    }

    @GetMapping("/getEmployeeById/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return service.getEmployeeById(id);
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody Employee emp){
        return service.addEmployee(emp);
    }

    @PutMapping("/updateEmployee/{id}")
    public String updateEmployee(@RequestBody Employee employee,@PathVariable Integer id){
        return service.updateEmployee(employee,id);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Integer id){
        return service.deleteEmployee(id);
    }

    @DeleteMapping("/deleteAllEmployees")
    public String deleteAllEmployees(){
        return service.deleteAllEmployees();
    }
}
