package org.example.setterinjectiondemo;

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

    @Value("101")
    public void setEmpID(int empID) {
        System.out.println("Inside setEmpid");
        this.empID = empID;
    }

    public String getName() {
        return name;
    }

    @Value("Ravi")
    public void setName(String name) {
        System.out.println("Inside setName");
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    @Value("10000.0")
    public void setSalary(double salary) {
        System.out.println("Inside setSalary");
        this.salary = salary;
    }

    // default constructor
    public Employee(){
        System.out.println("Inside Default Constructor");
    }


}
