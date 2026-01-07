package com.example.sample_gradle_project.service;

import com.example.sample_gradle_project.dto.Employee;
import com.example.sample_gradle_project.repository.EmployeeRepositoryVulnerable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTests {

    @Mock
    private EmployeeRepositoryVulnerable employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void getEmployee() {
        // Given
        Employee expectedEmployee = new Employee("Raj", "Kumar", 30);

        // When
        Employee actualEmployee = employeeService.getEmployee();

        // Then
        assertNotNull(actualEmployee);
        assertEquals(expectedEmployee.getFirstName(), actualEmployee.getFirstName());
        assertEquals(expectedEmployee.getLastName(), actualEmployee.getLastName());
        assertEquals(expectedEmployee.getAge(), actualEmployee.getAge());
    }

    @Test
    void getByFirstName_WithValidName() {
        // Given
        String firstName = "John";
        Employee expectedEmployee = new Employee("John", "Doe", 30);
        List<Employee> expectedEmployees = List.of(expectedEmployee);
        when(employeeRepository.findByFirstName(firstName)).thenReturn(expectedEmployees);

        // When
        List<Employee> actualEmployees = employeeService.getByFirstName(firstName);

        // Then
        assertNotNull(actualEmployees);
        assertEquals(1, actualEmployees.size());
        Employee actualEmployee = actualEmployees.get(0);
        assertNotNull(actualEmployee);
        assertEquals(expectedEmployee.getFirstName(), actualEmployee.getFirstName());
        assertEquals(expectedEmployee.getLastName(), actualEmployee.getLastName());
        assertEquals(expectedEmployee.getAge(), actualEmployee.getAge());
        verify(employeeRepository).findByFirstName(firstName);
    }

    @Test
    void getByFirstName_WithNullName() {
        // When
        List<Employee> actualEmployees = employeeService.getByFirstName(null);

        // Then
        assertEquals(Collections.emptyList(), actualEmployees);
    }

    @Test
    void getByFirstName_WithEmptyName() {
        // When
        List<Employee> actualEmployees = employeeService.getByFirstName("");

        // Then
        assertEquals(Collections.emptyList(), actualEmployees);
    }
}