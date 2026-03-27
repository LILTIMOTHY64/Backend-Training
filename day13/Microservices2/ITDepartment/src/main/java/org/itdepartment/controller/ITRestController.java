package org.itdepartment.controller;

import org.itdepartment.consumer.HRRestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ITRestController {
    @Autowired
    private HRRestConsumer itRestConsumer;

    // Get Leave Data from HR
    @GetMapping("/it/getLeaveData")
    public String getLeaveData() {
        boolean leaveData = itRestConsumer.getLeaveData();
        if (leaveData) {
            return "Monday is a Holiday.";
        } else {
            return "Failed to retrieve leave data from HR.";
        }
    }
}
