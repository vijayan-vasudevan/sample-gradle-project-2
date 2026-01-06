package com.example.sample_gradle_project.service;

import com.example.sample_gradle_project.dto.Employee;
import com.example.sample_gradle_project.repository.EmployeeRepositoryVulnerable;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepositoryVulnerable employeeRepository;

    public Employee getEmployee() {
        return new Employee("Raj", "Kumar", 30);
    }

    public List<Employee> getByFirstName(String firstName) {

        if (!StringUtils.isBlank(firstName)) {
            return employeeRepository.findByFirstName(firstName);
        }
        return Collections.emptyList();
    }

}