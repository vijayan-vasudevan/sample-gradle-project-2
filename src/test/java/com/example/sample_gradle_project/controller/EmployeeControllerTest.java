package com.example.sample_gradle_project.controller;

import com.example.sample_gradle_project.dto.Employee;
import com.example.sample_gradle_project.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testGetEmployee() {
        // Mock data
        Employee mockEmployee = new Employee("Raj", "Kumar", 30);
        when(employeeService.getEmployee()).thenReturn(mockEmployee);

        // Call method
        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        // Verify
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockEmployee, response.getBody());
    }

    @Test
    public void testGetByFirstName() {
        // Mock data
        List<Employee> mockEmployees = List.of(new Employee("John", "Doe", 30));
        when(employeeService.getByFirstName("John")).thenReturn(mockEmployees);

        // Call method
        ResponseEntity<List<Employee>> response = employeeController.getByFirstName("John");

        // Verify
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockEmployees, response.getBody());
    }
}