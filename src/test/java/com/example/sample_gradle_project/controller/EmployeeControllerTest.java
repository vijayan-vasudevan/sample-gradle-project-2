package com.example.sample_gradle_project.controller;

import com.example.sample_gradle_project.dto.Employee;
import com.example.sample_gradle_project.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEmployee_ShouldReturnEmployee_WhenCalled() {
        // Given
        Employee expectedEmployee = new Employee("John", "Doe", 30);
        when(employeeService.getEmployee()).thenReturn(expectedEmployee);

        // When
        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        // Then
        assertEquals(expectedEmployee, response.getBody());
        assertNotNull(response);
    }

    @Test
    void getByFirstName_ShouldReturnEmployeeList_WhenFirstNameIsProvided() {
        // Given
        String firstName = "John";
        Employee expectedEmployee = new Employee("John", "Doe", 30);
        List<Employee> expectedList = List.of(expectedEmployee);
        when(employeeService.getByFirstName(firstName)).thenReturn(expectedList);

        // When
        ResponseEntity<List<Employee>> response = employeeController.getByFirstName(firstName);

        // Then
        assertEquals(expectedList, response.getBody());
        assertNotNull(response);
    }
}