package com.example.sample_gradle_project.repository;

import com.example.sample_gradle_project.dto.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryVulnerableTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private EmployeeRepositoryVulnerable employeeRepositoryVulnerable;

    @Test
    public void testFindByFirstName() {
        // Mock data
        List<Employee> mockEmployees = List.of(new Employee("John", "Doe", 30));
        
        // Fix: Use explicit RowMapper type to resolve ambiguity
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(mockEmployees);

        // Call method
        List<Employee> result = employeeRepositoryVulnerable.findByFirstName("John");

        // Fix: Use doReturn for void methods or verify without return value
        verify(jdbcTemplate).query(anyString(), any(RowMapper.class));
        assert result.size() == 1;
    }
}