package org.example.studentcrud.Controllers;

import org.example.studentcrud.Entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MyController {

    ArrayList<Employee> emp = new ArrayList<Employee>();

    public MyController() {
        emp.add(new Employee(1, "John", 30, 5000, "Developer"));
        emp.add(new Employee(2, "Jane", 26, 4000, "Tester"));
        emp.add(new Employee(3, "Modi", 67, 5000, "PM"));
    }

    @GetMapping("/getAllEmployees")
    public ArrayList<Employee> getEmployee() {
        return emp;
    }

    @GetMapping("/getEmployee/{id}")
    public Employee getEmployeeByID(@PathVariable int id) {
        for (Employee e : emp) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody Employee e){
        emp.add(e);
        return "Employee Added Successfully";
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id){
        for (Employee e : emp){
            if (e.getId() == id){
                emp.remove(e);
                return "Employee with ID " + id + " successfully removed";
            }
        }
        return "Employee with ID " + id + " Not Found";
    }

    @PutMapping("/updateEmployee/{id}")
    public String updateEmployee(@RequestBody Employee e_new,@PathVariable int id){
        //Loop through emp and find the required id
        for (Employee e:emp){
            if (e.getId() == id){
                e.setName(e_new.getName());
                e.setAge(e_new.getAge());
                e.setSalary(e_new.getSalary());
                e.setDesignation(e_new.getDesignation());
                return "Employee Updated Successfully";
            }
        }
        return "Employee Not Found";
    }
}
