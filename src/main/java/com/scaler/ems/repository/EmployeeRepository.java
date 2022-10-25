package com.scaler.ems.repository;

import com.scaler.ems.entities.Employee;

import java.util.Optional;

public interface EmployeeRepository {

    Optional<Employee> getEmployeeByEmployeeId(String employeeId);
}
