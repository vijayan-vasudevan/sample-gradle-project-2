package com.example.sample_gradle_project.controller;

import com.example.sample_gradle_project.controller.EmployeeController;
import com.example.sample_gradle_project.service.EmployeeService;
import com.example.sample_gradle_project.dto.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockHttpServletRequest request;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
    }

    @Test
    public void getEmployee_success() {
        // Arrange
        String userAgent = "Mozilla/5.0";
        request.addHeader("User-Agent", userAgent);
        Employee mockEmployee = new Employee("John", "Doe", 30);
        when(employeeService.getEmployee()).thenReturn(mockEmployee);

        // Act
        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService).getEmployee();
    }

    @Test
    public void getEmployee_null_user_agent() {
        // Arrange
        request = new MockHttpServletRequest(); // No User-Agent header added
        Employee mockEmployee = new Employee("John", "Doe", 30);
        when(employeeService.getEmployee()).thenReturn(mockEmployee);

        // Act
        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService).getEmployee();
    }

    @Test
    public void getEmployee_empty_user_agent() {
        // Arrange
        request.addHeader("User-Agent", "");
        Employee mockEmployee = new Employee("John", "Doe", 30);
        when(employeeService.getEmployee()).thenReturn(mockEmployee);

        // Act
        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService).getEmployee();
    }

    @Test
    public void getEmployee_valid_user_agent() {
        // Arrange
        String userAgent = "CustomAgent/1.0";
        request.addHeader("User-Agent", userAgent);
        Employee mockEmployee = new Employee("John", "Doe", 30);
        when(employeeService.getEmployee()).thenReturn(mockEmployee);

        // Act
        ResponseEntity<Employee> response = employeeController.getEmployee(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService).getEmployee();
    }

    @Test
    public void getByFirstName_success() {
        // Arrange
        String firstName = "John";
        List<Employee> mockEmployees = Arrays.asList(new Employee("John", "Doe", 30));
        when(employeeService.getByFirstName(firstName)).thenReturn(mockEmployees);

        // Act
        ResponseEntity<List<Employee>> response = employeeController.getByFirstName(firstName);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployees, response.getBody());
        verify(employeeService).getByFirstName(firstName);
    }

    @Test
    public void getByFirstName_null_first_name() {
        // Arrange
        String firstName = null;
        List<Employee> mockEmployees = Arrays.asList();
        when(employeeService.getByFirstName(any())).thenReturn(mockEmployees);

        // Act
        ResponseEntity<List<Employee>> response = employeeController.getByFirstName(firstName);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployees, response.getBody());
        verify(employeeService).getByFirstName(any());
    }

    @Test
    public void getByFirstName_empty_first_name() {
        // Arrange
        String firstName = "";
        List<Employee> mockEmployees = Arrays.asList();
        when(employeeService.getByFirstName(any())).thenReturn(mockEmployees);

        // Act
        ResponseEntity<List<Employee>> response = employeeController.getByFirstName(firstName);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployees, response.getBody());
        verify(employeeService).getByFirstName(any());
    }

    @Test
    public void getByFirstName_valid_first_name() {
        // Arrange
        String firstName = "Jane";
        List<Employee> mockEmployees = Arrays.asList(new Employee("Jane", "Smith", 25));
        when(employeeService.getByFirstName(firstName)).thenReturn(mockEmployees);

        // Act
        ResponseEntity<List<Employee>> response = employeeController.getByFirstName(firstName);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockEmployees, response.getBody());
        verify(employeeService).getByFirstName(firstName);
    }
}