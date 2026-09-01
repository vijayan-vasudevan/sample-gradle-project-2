package com.example.sample_gradle_project.repository;

import com.example.sample_gradle_project.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryVulnerable {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Fixed: using PreparedStatement with parameterized query to prevent SQL injection
    public List<Employee> findByFirstName(String firstName) {
        String sql = "SELECT first_name, last_name, age FROM employee WHERE first_name = ?";

        return jdbcTemplate.query(sql, new Object[]{firstName}, (rs, rowNum) ->
                new Employee(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age")
                )
        );
    }
}
