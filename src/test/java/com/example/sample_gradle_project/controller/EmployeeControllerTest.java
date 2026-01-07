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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void getEmployee_ShouldReturnEmployee() {
        // Given
        Employee expectedEmployee = new Employee("Raj", "Kumar", 30);
        when(employeeService.getEmployee()).thenReturn(expectedEmployee);

        // When
        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        // Then
        assertEquals(ResponseEntity.ok(expectedEmployee), response);
        verify(employeeService).getEmployee();
    }

    @Test
    void getByFirstName_ShouldReturnEmployeeList() {
        // Given
        String firstName = "John";
        List<Employee> expectedEmployees = List.of(new Employee("John", "Doe", 30));
        when(employeeService.getByFirstName(firstName)).thenReturn(expectedEmployees);

        // When
        ResponseEntity<List<Employee>> response = employeeController.getByFirstName(firstName);

        // Then
        assertEquals(ResponseEntity.ok(expectedEmployees), response);
        verify(employeeService).getByFirstName(firstName);
    }
}