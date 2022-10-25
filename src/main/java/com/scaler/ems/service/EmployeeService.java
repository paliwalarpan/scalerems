package com.scaler.ems.service;

import com.scaler.ems.model.EmployeeBO;

import java.util.List;

interface EmployeeService {

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
    void saveEmployee(EmployeeBO employee);

    /**
     * Delete employee
     *
     * @param id
     */
    void deleteEmployeeById(long id);

    List<EmployeeBO> searchEmployeeByFirstName(String searchTerm);

}
