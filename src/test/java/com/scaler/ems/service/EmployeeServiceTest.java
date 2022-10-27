package com.scaler.ems.service;

import com.scaler.ems.entities.Employee;
import com.scaler.ems.exception.EmployeeNotFoundException;
import com.scaler.ems.model.EmployeeBO;
import com.scaler.ems.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        when(employeeRepository.findByEmployeeId(validEmployeeId)).thenReturn(Optional.of(new Employee(validEmployeeId)));
        EmployeeBO myDetails = employeeService.fetchEmployee(validEmployeeId);
        assertNotNull(myDetails);
        assertEquals(validEmployeeId, myDetails.getEmployeeId());
    }

    @Test
    void should_throw_exception_employee_with_invalid_id() {
        String invalidEmployeeId = "EMP-99999";
        when(employeeRepository.findByEmployeeId(invalidEmployeeId)).thenReturn(Optional.empty());
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
        String empId = "Emp-12345";

        EmployeeBO employeeBO = new EmployeeBO();
        employeeBO.setEmployeeId(empId);

        Employee entity = new Employee();
        entity.setEmployeeId(empId);
        when(employeeRepository.save(Mockito.any())).thenReturn(entity);
        assertDoesNotThrow(() -> employeeService.saveEmployee(employeeBO));
    }

    @Test
    void testDeleteEmployee() {
        String empToDelete = "EMP-12345";
        Employee employee = new Employee(empToDelete);
        EmployeeBO employeeBO = new EmployeeBO();
        employeeBO.setEmployeeId(empToDelete);

        when(employeeRepository.findByEmployeeId(empToDelete)).thenReturn(Optional.of(new Employee(empToDelete)));
        doNothing().when(employeeRepository).delete(employee);
        assertDoesNotThrow(() -> employeeService.deleteEmployeeById(empToDelete));
    }

    @Test
    void testSearchEmployee() {
        List<Employee> employees = List.of(new Employee("Emp-12345"), new Employee("Emp-67890"));

        String firstNameStartsWith = "bo";
        when(employeeRepository.searchEmployee(firstNameStartsWith + "%")).thenReturn(employees);
        List<EmployeeBO> searchResult = employeeService.searchEmployeeByFirstName(firstNameStartsWith);
        assertNotNull(searchResult);
        assertEquals(employees.size(), searchResult.size());

    }

}