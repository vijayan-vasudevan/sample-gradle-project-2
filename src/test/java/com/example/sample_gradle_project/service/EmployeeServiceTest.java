package com.example.sample_gradle_project.service;

import com.example.sample_gradle_project.dto.Employee;
import com.example.sample_gradle_project.repository.EmployeeRepositoryVulnerable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepositoryVulnerable employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEmployee_ShouldReturnEmployee_WhenCalled() {
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
    void getByFirstName_ShouldReturnEmployeeList_WhenFirstNameIsProvided() {
        // Given
        String firstName = "John";
        Employee expectedEmployee = new Employee("John", "Doe", 30);
        List<Employee> expectedList = List.of(expectedEmployee);
        when(employeeRepository.findByFirstName(firstName)).thenReturn(expectedList);

        // When
        List<Employee> actualList = employeeService.getByFirstName(firstName);

        // Then
        assertEquals(expectedList, actualList);
        verify(employeeRepository).findByFirstName(firstName);
    }

    @Test
    void getByFirstName_ShouldReturnEmptyList_WhenFirstNameIsBlank() {
        // Given
        String firstName = "   ";

        // When
        List<Employee> actualList = employeeService.getByFirstName(firstName);

        // Then
        assertTrue(actualList.isEmpty());
        verify(employeeRepository, never()).findByFirstName(anyString());
    }
}