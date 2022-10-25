package com.scaler.ems.service;

import com.scaler.ems.entities.Employee;
import com.scaler.ems.exception.EmployeeNotFoundException;
import com.scaler.ems.model.EmployeeBO;
import com.scaler.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<EmployeeBO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::convertToBo).collect(Collectors.toList());
    }

    @Override
    public void saveEmployee(EmployeeBO employee) {
        employeeRepository.save(convertToEntity(employee));
    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeBO> searchEmployeeByFirstName(String searchTerm) {
        return employeeRepository.searchEmployee(searchTerm).stream().map(this::convertToBo).collect(Collectors.toList());
    }

    private EmployeeBO convertToBo(Employee emp) {
        EmployeeBO employeeBO = new EmployeeBO();
        employeeBO.setEmployeeId(emp.getEmployeeId());
        return employeeBO;
    }

    private Employee convertToEntity(EmployeeBO employeeBO) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeBO.getEmployeeId());
        return employee;
    }
}
