package org.example.setterinjectiondemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {

    private Employee emp;

    public EmployeeService(){
        System.out.println("Inside Employee Service Constructor");
    }

    @Autowired
    public void setEmp(Employee emp){
        System.out.println("Inside Set Employee");
        this.emp = emp;
    }

    void display(){
        System.out.println("Employee ID is " + emp.getEmpID());
        System.out.println("Employee Name is " + emp.getName());
        System.out.println("Employee Salary is " + emp.getSalary());
    }
}
