package com.scaler.ems.service;

import com.scaler.ems.model.EmployeeBO;

interface EmployeeService {

    /**
     * Fetches employee by employee ID.
     *
     * @param employeeId Unique identifier for employee
     * @return EmployeeBo
     */
    EmployeeBO fetchEmployee(String employeeId);
}
