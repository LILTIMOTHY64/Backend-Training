package org.example.constructorinjection;

import org.springframework.stereotype.Component;

@Component
public class EmployeeService {
    private final Employee emp;

    public EmployeeService(Employee emp) {
        System.out.println("Inside EmployeeService Constructor");
        this.emp = emp;
    }

    public void display(){
        System.out.println("Employee ID is " + emp.getEmpID());
        System.out.println("Employee Name is " + emp.getName());
        System.out.println("Employee Salary is " + emp.getSalary());
    }
}
