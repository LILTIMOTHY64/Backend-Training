package org.example;

public class Department {
    private final String name;
    private final String location;

    public Department(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Department{name='" + name + "', location='" + location + "'}";
    }
}
