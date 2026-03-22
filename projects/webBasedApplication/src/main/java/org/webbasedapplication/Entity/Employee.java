package org.webbasedapplication.Entity;

public class Employee {
    private String name;
    private String designation;
    private int age;
    private int salary;

    public Employee() {}

    public Employee(String name, String designation, int age, int salary) {
        this.name = name;
        this.designation = designation;
        this.age = age;
        this.salary = salary;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }
}
