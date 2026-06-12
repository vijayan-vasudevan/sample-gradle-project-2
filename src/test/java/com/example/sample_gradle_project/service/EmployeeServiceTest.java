package com.example.sample_gradle_project.service;

import com.example.sample_gradle_project.service.EmployeeService;
import com.example.sample_gradle_project.repository.EmployeeRepositoryVulnerable;
import com.example.sample_gradle_project.dto.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepositoryVulnerable employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testGetEmployee_Success() {
        Employee result = employeeService.getEmployee();
        assertNotNull(result);
        assertEquals("Raj", result.getFirstName());
        assertEquals("Kumar", result.getLastName());
        assertEquals(30, result.getAge());
    }

    @Test
    public void testGetByFirstName_Success() {
        List<Employee> expected = Collections.singletonList(new Employee("Raj", "Kumar", 30));
        when(employeeRepository.findByFirstName("Raj")).thenReturn(expected);

        List<Employee> result = employeeService.getByFirstName("Raj");

        assertEquals(expected, result);
        verify(employeeRepository).findByFirstName("Raj");
    }

    @Test
    public void testGetByFirstName_NullInput() {
        List<Employee> result = employeeService.getByFirstName(null);
        assertTrue(result.isEmpty());
        verifyNoInteractions(employeeRepository);
    }

    @Test
    public void testGetByFirstName_EmptyInput() {
        List<Employee> result = employeeService.getByFirstName("");
        assertTrue(result.isEmpty());
        verifyNoInteractions(employeeRepository);
    }

    @Test
    public void testGetByFirstName_WhitespaceInput() {
        List<Employee> result = employeeService.getByFirstName("   ");
        assertTrue(result.isEmpty());
        verifyNoInteractions(employeeRepository);
    }
}