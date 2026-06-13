package com.example.sample_gradle_project.repository;

import com.example.sample_gradle_project.dto.Employee;
import com.example.sample_gradle_project.repository.EmployeeRepositoryVulnerable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeRepositoryVulnerableTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private EmployeeRepositoryVulnerable employeeRepositoryVulnerable;

    private List<Employee> mockEmployeeList;

    @BeforeEach
    public void setUp() {
        mockEmployeeList = Arrays.asList(
                new Employee("John", "Doe", 30),
                new Employee("Jane", "Smith", 25)
        );
    }

    @Test
    public void findByFirstName_success() {
        // CASE A — mock IS called for this input
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(mockEmployeeList);

        List<Employee> result = employeeRepositoryVulnerable.findByFirstName("John");

        assertEquals(mockEmployeeList, result);
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(RowMapper.class));
    }

    @Test
    public void findByFirstName_null_input() {
        // CASE A — mock IS called for this input
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(mockEmployeeList);

        List<Employee> result = employeeRepositoryVulnerable.findByFirstName(null);

        assertEquals(mockEmployeeList, result);
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(RowMapper.class));
    }

    @Test
    public void findByFirstName_empty_string() {
        // CASE A — mock IS called for this input
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(mockEmployeeList);

        List<Employee> result = employeeRepositoryVulnerable.findByFirstName("");

        assertEquals(mockEmployeeList, result);
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(RowMapper.class));
    }

    @Test
    public void findByFirstName_special_characters() {
        // CASE A — mock IS called for this input
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(mockEmployeeList);

        List<Employee> result = employeeRepositoryVulnerable.findByFirstName("John@#$%");

        assertEquals(mockEmployeeList, result);
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(RowMapper.class));
    }

    @Test
    public void findByFirstName_long_string() {
        // CASE A — mock IS called for this input
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(mockEmployeeList);

        String longString = "A".repeat(1000);
        List<Employee> result = employeeRepositoryVulnerable.findByFirstName(longString);

        assertEquals(mockEmployeeList, result);
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(RowMapper.class));
    }

    @Test
    public void findByFirstName_whitespace_only() {
        // CASE A — mock IS called for this input
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(mockEmployeeList);

        List<Employee> result = employeeRepositoryVulnerable.findByFirstName("   ");

        assertEquals(mockEmployeeList, result);
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(RowMapper.class));
    }
}