package org.example;

public class Employee {
    private final String name;
    private final int id;
    private final Department department;

    // Constructor for dependency injection
    public Employee(int id, String name, Department department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', department=" + department + "}";
    }
}
