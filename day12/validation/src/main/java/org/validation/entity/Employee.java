package org.validation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;


@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // Validation 1
    @NotBlank(message = "Name cannot be blank")
    private String name;
    // Validation 2
    @Positive(message = "Salary must be positive")
    private double salary;
    // Validation 3
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits and contain only numbers")
    private String phoneNumber;



    public Employee(Integer id, String name, double salary, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
    }

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
