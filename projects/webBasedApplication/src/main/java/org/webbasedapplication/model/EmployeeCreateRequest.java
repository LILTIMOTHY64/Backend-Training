package org.webbasedapplication.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EmployeeCreateRequest {

    @NotBlank(message = "Name must not be blank.")
    @Pattern(regexp = "^[A-Za-z]+(?: [A-Za-z]+){0,2}$", message = "Invalid Name: Only alphabets allowed, max 2 spaces.")
    private String name;

    @Pattern(
        regexp = "(?i)Programmer|Tester|Manager",
        message = "Invalid Designation: Must be Programmer, Tester, or Manager."
    )
    @NotBlank(message = "Designation must not be blank.")
    private String designation;

    @Min(value = 19, message = "Invalid Age: Must be between 19 and 60.")
    @Max(value = 60, message = "Invalid Age: Must be between 19 and 60.")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
