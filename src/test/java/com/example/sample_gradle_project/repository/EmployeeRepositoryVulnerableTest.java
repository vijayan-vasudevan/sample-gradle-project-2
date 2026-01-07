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
class EmployeeRepositoryVulnerableTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private EmployeeRepositoryVulnerable employeeRepository;

    @Test
    void findByFirstName_ShouldReturnEmployeeList() {
        // Given
        String firstName = "John";
        List<Employee> expectedEmployees = List.of(new Employee("John", "Doe", 30));

        // When
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedEmployees);
        List<Employee> actualEmployees = employeeRepository.findByFirstName(firstName);

        // Then
        verify(jdbcTemplate).query(anyString(), any(RowMapper.class));
        assert actualEmployees.equals(expectedEmployees);
    }
}