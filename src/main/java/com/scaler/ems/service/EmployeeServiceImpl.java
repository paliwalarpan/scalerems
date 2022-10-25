package com.scaler.ems.service;

import com.scaler.ems.entities.Employee;
import com.scaler.ems.exception.EmployeeNotFoundException;
import com.scaler.ems.model.EmployeeBO;
import com.scaler.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeBO fetchEmployee(String employeeId) {
        Optional<Employee> employeeByEmployeeId = employeeRepository.getEmployeeByEmployeeId(employeeId);
        Employee emp = employeeByEmployeeId.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with emp id " + employeeId));
        //convert o to employeeBo
        return convertToBo(emp);

    }

    private EmployeeBO convertToBo(Employee emp) {
        EmployeeBO employeeBO = new EmployeeBO();
        employeeBO.setEmployeeId(emp.getEmployeeId());
        return employeeBO;
    }
}
