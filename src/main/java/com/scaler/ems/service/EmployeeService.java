package com.scaler.ems.service;

import com.scaler.ems.model.EmployeeBO;

import java.util.List;

public interface EmployeeService {

    /**
     * Fetches employee by employee ID.
     *
     * @param employeeId Unique identifier for employee
     * @return EmployeeBo
     */
    EmployeeBO fetchEmployee(String employeeId);

    /**
     * Return all the employees
     *
     * @return list of employee
     */
    List<EmployeeBO> getAllEmployees();

    /**
     * Save employee
     *
     * @param employee
     */
    EmployeeBO saveEmployee(EmployeeBO employee);

    /**
     * Delete employee
     *
     * @param empId
     */
    void deleteEmployeeById(String empId);

    List<EmployeeBO> searchEmployeeByFirstName(String searchTerm);

    EmployeeBO updateEmployee(String employeeId, EmployeeBO employeeBO);

}
