package com.scaler.ems.repository;

import com.scaler.ems.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Optional<Employee> getEmployeeByEmployeeId(String employeeId);

    List<Employee> findAll();

    void save(Employee convertToEntity);

    void deleteById(long id);

    List<Employee> searchEmployee(String searchTerm);
}
