package com.lcaohoanq.Spring_Snake_Game.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @PostMapping("/employee/register")
    public void registerEmployee() {
        // Register employee
    }

}
