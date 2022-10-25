package com.scaler.ems.service;

import com.scaler.ems.entities.Employee;
import com.scaler.ems.exception.EmployeeNotFoundException;
import com.scaler.ems.model.EmployeeBO;
import com.scaler.ems.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    EmployeeService employeeService;
    EmployeeRepository employeeRepository;

    @BeforeEach
    void initialize() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void should_return_employee_with_valid_id() {
        String validEmployeeId = "EMP-12345";
        Mockito.when(employeeRepository.getEmployeeByEmployeeId(validEmployeeId)).thenReturn(Optional.of(new Employee(validEmployeeId)));
        EmployeeBO myDetails = employeeService.fetchEmployee(validEmployeeId);
        assertNotNull(myDetails);
        assertEquals(validEmployeeId, myDetails.getEmployeeId());
    }

    @Test
    void should_throw_exception_employee_with_invalid_id() {
        String invalidEmployeeId = "EMP-99999";
        Mockito.when(employeeRepository.getEmployeeByEmployeeId(invalidEmployeeId)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.fetchEmployee(invalidEmployeeId));
    }

}