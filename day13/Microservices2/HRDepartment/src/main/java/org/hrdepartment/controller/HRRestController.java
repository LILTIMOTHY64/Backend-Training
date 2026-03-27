package org.hrdepartment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HRRestController {

    @GetMapping("/hr/getLeaveData")
    public boolean getLeaveData() {
        return false;
    }
}
