package com.scaler.ems.service;

import com.scaler.ems.exception.EmployeeNotFoundException;
import com.scaler.ems.model.EmployeeBO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    EmployeeService employeeService;

    @BeforeEach
    void initialize() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    void should_return_employee_with_valid_id() {
        String validEmployeeId = "EMP-12345";
        EmployeeBO myDetails = employeeService.fetchEmployee(validEmployeeId);
        assertNotNull(myDetails);
        assertEquals(validEmployeeId, myDetails.getEmployeeId());
    }

    @Test
    void should_throw_exception_employee_with_invalid_id() {
        String invalidEmployeeId = "EMP-12345";
        EmployeeBO myDetails = employeeService.fetchEmployee(invalidEmployeeId);
        assertNull(myDetails);

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.fetchEmployee(invalidEmployeeId));
    }

}