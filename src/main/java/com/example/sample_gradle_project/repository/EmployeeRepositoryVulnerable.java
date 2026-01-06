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

    // Vulnerable: user input 'name' concatenated directly into SQL
    public List<Employee> findByFirstName(String firstName) {
        String sql = "SELECT first_name, last_name, age FROM employee WHERE first_name = '" + firstName + "'";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Employee(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age")
                )
        );
    }
}
