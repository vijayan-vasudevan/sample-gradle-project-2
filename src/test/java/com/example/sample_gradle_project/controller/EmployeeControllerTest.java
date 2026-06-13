package com.example.sample_gradle_project.controller;

import com.example.sample_gradle_project.controller.EmployeeController;
import com.example.sample_gradle_project.service.EmployeeService;
import com.example.sample_gradle_project.dto.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private EmployeeController employeeController;

    private Employee mockEmployee;

    @BeforeEach
    public void setUp() {
        mockEmployee = new Employee("John", "Doe", 30);
    }

    @Test
    public void getEmployee_success() {
        when(employeeService.getEmployee()).thenReturn(mockEmployee);
        when(request.getHeader("User-Agent")).thenReturn("Mozilla/5.0");

        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService).getEmployee();
        verify(request).getHeader("User-Agent");
    }

    @Test
    public void getEmployee_null_user_agent() {
        when(employeeService.getEmployee()).thenReturn(mockEmployee);
        when(request.getHeader("User-Agent")).thenReturn(null);

        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService).getEmployee();
        verify(request).getHeader("User-Agent");
    }

    @Test
    public void getEmployee_empty_user_agent() {
        when(employeeService.getEmployee()).thenReturn(mockEmployee);
        when(request.getHeader("User-Agent")).thenReturn("");

        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService).getEmployee();
        verify(request).getHeader("User-Agent");
    }

    @Test
    public void getEmployee_user_agent_with_special_characters() {
        when(employeeService.getEmployee()).thenReturn(mockEmployee);
        when(request.getHeader("User-Agent")).thenReturn("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService).getEmployee();
        verify(request).getHeader("User-Agent");
    }

    @Test
    public void getEmployee_user_agent_with_spaces() {
        when(employeeService.getEmployee()).thenReturn(mockEmployee);
        when(request.getHeader("User-Agent")).thenReturn("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");

        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService).getEmployee();
        verify(request).getHeader("User-Agent");
    }

    @Test
    public void getEmployee_user_agent_with_numbers() {
        when(employeeService.getEmployee()).thenReturn(mockEmployee);
        when(request.getHeader("User-Agent")).thenReturn("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService).getEmployee();
        verify(request).getHeader("User-Agent");
    }

    @Test
    public void getEmployee_user_agent_with_unicode_characters() {
        when(employeeService.getEmployee()).thenReturn(mockEmployee);
        when(request.getHeader("User-Agent")).thenReturn("Mozilla/5.0 (iPhone; CPU iPhone OS 14_6 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1 你好世界");

        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService).getEmployee();
        verify(request).getHeader("User-Agent");
    }
}