package com.example.sample_gradle_project.repository;

import com.example.sample_gradle_project.dto.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeRepositoryVulnerableTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private EmployeeRepositoryVulnerable employeeRepositoryVulnerable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByFirstName_ShouldReturnEmployeeList_WhenFirstNameIsProvided() {
        // Given
        String firstName = "John";
        Employee expectedEmployee = new Employee("John", "Doe", 30);
        List<Employee> expectedList = List.of(expectedEmployee);

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedList);

        // When
        List<Employee> actualList = employeeRepositoryVulnerable.findByFirstName(firstName);

        // Then
        assertEquals(expectedList, actualList);
        verify(jdbcTemplate).query(anyString(), any(RowMapper.class));
    }
}