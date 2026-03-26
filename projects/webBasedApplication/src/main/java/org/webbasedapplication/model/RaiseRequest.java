package org.webbasedapplication.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class RaiseRequest {
    @NotBlank(message = "Name must not be blank.")
    private String name;

    @Min(value = 1, message = "Max Allowed Raise is 10%.")
    @Max(value = 10, message = "Max Allowed Raise is 10%.")
    private int percent;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
