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
        // Arrange
        String firstName = "John";
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(mockEmployeeList);

        // Act
        List<Employee> result = employeeRepositoryVulnerable.findByFirstName(firstName);

        // Assert
        assertEquals(mockEmployeeList, result);
        verify(jdbcTemplate).query(anyString(), eq(new Object[]{firstName}), any(RowMapper.class));
    }

    @Test
    public void findByFirstName_null_input() {
        // Arrange
        String firstName = null;
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(mockEmployeeList);

        // Act
        List<Employee> result = employeeRepositoryVulnerable.findByFirstName(firstName);

        // Assert
        assertEquals(mockEmployeeList, result);
        verify(jdbcTemplate).query(anyString(), eq(new Object[]{firstName}), any(RowMapper.class));
    }

    @Test
    public void findByFirstName_empty_string() {
        // Arrange
        String firstName = "";
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(mockEmployeeList);

        // Act
        List<Employee> result = employeeRepositoryVulnerable.findByFirstName(firstName);

        // Assert
        assertEquals(mockEmployeeList, result);
        verify(jdbcTemplate).query(anyString(), eq(new Object[]{firstName}), any(RowMapper.class));
    }

    @Test
    public void findByFirstName_special_characters() {
        // Arrange
        String firstName = "John@#$%";
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(mockEmployeeList);

        // Act
        List<Employee> result = employeeRepositoryVulnerable.findByFirstName(firstName);

        // Assert
        assertEquals(mockEmployeeList, result);
        verify(jdbcTemplate).query(anyString(), eq(new Object[]{firstName}), any(RowMapper.class));
    }

    @Test
    public void findByFirstName_long_string() {
        // Arrange
        String firstName = "A".repeat(1000);
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(mockEmployeeList);

        // Act
        List<Employee> result = employeeRepositoryVulnerable.findByFirstName(firstName);

        // Assert
        assertEquals(mockEmployeeList, result);
        verify(jdbcTemplate).query(anyString(), eq(new Object[]{firstName}), any(RowMapper.class));
    }

    @Test
    public void findByFirstName_whitespace_only() {
        // Arrange
        String firstName = "   ";
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(mockEmployeeList);

        // Act
        List<Employee> result = employeeRepositoryVulnerable.findByFirstName(firstName);

        // Assert
        assertEquals(mockEmployeeList, result);
        verify(jdbcTemplate).query(anyString(), eq(new Object[]{firstName}), any(RowMapper.class));
    }
}