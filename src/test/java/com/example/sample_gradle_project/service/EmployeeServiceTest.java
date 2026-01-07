package com.example.sample_gradle_project.service;

import com.example.sample_gradle_project.dto.Employee;
import com.example.sample_gradle_project.repository.EmployeeRepositoryVulnerable;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepositoryVulnerable employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void getEmployee_ShouldReturnEmployee() {
        // Given
        Employee expectedEmployee = new Employee("Raj", "Kumar", 30);

        // When
        Employee actualEmployee = employeeService.getEmployee();

        // Then
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void getByFirstName_WithValidName_ShouldReturnEmployeeList() {
        // Given
        String firstName = "John";
        List<Employee> expectedEmployees = List.of(new Employee("John", "Doe", 30));
        when(employeeRepository.findByFirstName(firstName)).thenReturn(expectedEmployees);

        // When
        List<Employee> actualEmployees = employeeService.getByFirstName(firstName);

        // Then
        assertEquals(expectedEmployees, actualEmployees);
        verify(employeeRepository).findByFirstName(firstName);
    }

    @Test
    void getByFirstName_WithNullName_ShouldReturnEmptyList() {
        // Given
        String firstName = null;

        // When
        List<Employee> actualEmployees = employeeService.getByFirstName(firstName);

        // Then
        assertEquals(Collections.emptyList(), actualEmployees);
        verify(employeeRepository, never()).findByFirstName(firstName);
    }

    @Test
    void getByFirstName_WithEmptyName_ShouldReturnEmptyList() {
        // Given
        String firstName = "";

        // When
        List<Employee> actualEmployees = employeeService.getByFirstName(firstName);

        // Then
        assertEquals(Collections.emptyList(), actualEmployees);
        verify(employeeRepository, never()).findByFirstName(firstName);
    }
}