package org.example.studentcrud.Entity;

public class Employee {

    private int id;
    private String name;
    private int age;
    private int salary;
    private String designation;

    public Employee(int id,String name,int age,int salary,String designation){
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
    }

    public Employee(){
        // Default Constructor
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
