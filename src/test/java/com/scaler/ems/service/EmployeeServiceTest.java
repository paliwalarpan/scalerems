package com.scaler.ems.service;

import com.scaler.ems.entities.Employee;
import com.scaler.ems.exception.EmployeeNotFoundException;
import com.scaler.ems.model.EmployeeBO;
import com.scaler.ems.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void initialize() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void should_return_employee_with_valid_id() {
        String validEmployeeId = "EMP-12345";
        when(employeeRepository.getEmployeeByEmployeeId(validEmployeeId)).thenReturn(Optional.of(new Employee(validEmployeeId)));
        EmployeeBO myDetails = employeeService.fetchEmployee(validEmployeeId);
        assertNotNull(myDetails);
        assertEquals(validEmployeeId, myDetails.getEmployeeId());
    }

    @Test
    void should_throw_exception_employee_with_invalid_id() {
        String invalidEmployeeId = "EMP-99999";
        when(employeeRepository.getEmployeeByEmployeeId(invalidEmployeeId)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.fetchEmployee(invalidEmployeeId));
    }

    @Test
    void testFetchAllEmployee() {
        List<Employee> employees = List.of(new Employee("Emp-12345"), new Employee("Emp-67890"));
        when(employeeRepository.findAll()).thenReturn(employees);
        List<EmployeeBO> allEmployees = employeeService.getAllEmployees();
        assertNotNull(allEmployees);
        assertEquals(employees.size(), allEmployees.size());
    }

    @Test
    void testSaveEmployee() {
        EmployeeBO employeeBO = new EmployeeBO();
        employeeBO.setEmployeeId("Emp-12345");
        doNothing().when(employeeRepository).save(any());
        assertDoesNotThrow(() -> employeeService.saveEmployee(employeeBO));
    }

    @Test
    void testDeleteEmployee() {
        long empToDelete = 5;
        doNothing().when(employeeRepository).deleteById(empToDelete);
        assertDoesNotThrow(() -> employeeService.deleteEmployeeById(empToDelete));
    }

    @Test
    void testSearchEmployee() {
        List<Employee> employees = List.of(new Employee("Emp-12345"), new Employee("Emp-67890"));

        String firstNameStartsWith = "bo";
        when(employeeRepository.searchEmployee(firstNameStartsWith)).thenReturn(employees);
        List<EmployeeBO> searchResult = employeeService.searchEmployeeByFirstName(firstNameStartsWith);
        assertNotNull(searchResult);
        assertEquals(employees.size(), searchResult.size());

    }

}