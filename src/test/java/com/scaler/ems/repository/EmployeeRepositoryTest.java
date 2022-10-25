package com.scaler.ems.repository;

import com.scaler.ems.entities.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void initialize() {
        Employee employee = new Employee("EMP-12345");
        employee.setFirstName("FN");
        employee.setLastName("LN");
        employee.setEmail("fn.ln@testmail.com");
        Employee savedEmp = employeeRepository.save(employee);
        assertNotNull(savedEmp);
    }

    @Test
    public void testRepositoryCreated() {
        assertNotNull(employeeRepository);
    }

    @Test
    void test_findByEmployeeId() {
        Optional<Employee> byEmployeeId = employeeRepository.findByEmployeeId("EMP-12345");
        assertTrue(byEmployeeId.isPresent());
        assertEquals("EMP-12345", byEmployeeId.get().getEmployeeId());
    }

    @Test
    void test_searchEmployee() {
        List<Employee> employees = employeeRepository.searchEmployee("f%");
        assertNotNull(employees);
        assertEquals(1, employees.size());
    }

    @AfterEach
    void destroy() {
        employeeRepository.deleteAll();
    }
}