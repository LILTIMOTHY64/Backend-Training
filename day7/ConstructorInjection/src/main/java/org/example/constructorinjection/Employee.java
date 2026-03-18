package org.example.constructorinjection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Employee {
    private int empID;
    private String name;
    private double salary;

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(
            @Value("101") int empID,
            @Value("Ravi") String name,
            @Value("10000") float salary
    ){
        System.out.println("Inside Employee Constructor");
        this.empID = empID;
        this.name = name;
        this.salary = salary;
    }
}
