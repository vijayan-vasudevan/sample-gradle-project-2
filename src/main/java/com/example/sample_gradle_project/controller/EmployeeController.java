package com.example.sample_gradle_project.controller;

import com.example.sample_gradle_project.dto.Employee;
import com.example.sample_gradle_project.service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Employee> getEmployee(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        logger.info("User-Agent: " + userAgent);
        return ResponseEntity.ok(employeeService.getEmployee());
    }
 
    @GetMapping("/by-first-name")
    public ResponseEntity<List<Employee>> getByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(employeeService.getByFirstName(firstName));
    }
}
