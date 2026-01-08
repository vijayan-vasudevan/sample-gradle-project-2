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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepositoryVulnerable employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testGetEmployee() {
        // Call method
        Employee result = employeeService.getEmployee();

        // Verify
        assertEquals("Raj", result.getFirstName());
    }

    @Test
    public void testGetByFirstName() {
        // Mock data
        List<Employee> mockEmployees = List.of(new Employee("John", "Doe", 30));
        when(employeeRepository.findByFirstName("John")).thenReturn(mockEmployees);

        // Call method
        List<Employee> result = employeeService.getByFirstName("John");

        // Verify
        assertEquals(1, result.size());
    }

    @Test
    public void testGetByFirstNameWithNullInput() {
        // Call method
        List<Employee> result = employeeService.getByFirstName(null);

        // Verify
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetByFirstNameWithEmptyInput() {
        // Call method
        List<Employee> result = employeeService.getByFirstName("");

        // Verify
        assertTrue(result.isEmpty());
    }
}