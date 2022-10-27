package com.scaler.ems.service;

import com.scaler.ems.entities.Employee;
import com.scaler.ems.exception.EmployeeNotFoundException;
import com.scaler.ems.model.EmployeeBO;
import com.scaler.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
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
        Optional<Employee> employeeByEmployeeId = employeeRepository.findByEmployeeId(employeeId);
        Employee emp = employeeByEmployeeId.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with emp id " + employeeId));
        return convertToBo(emp);

    }

    @Override
    public List<EmployeeBO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::convertToBo).collect(Collectors.toList());
    }

    @Override
    @Secured("ROLE_ADMIN")
    public EmployeeBO saveEmployee(EmployeeBO employee) {
        employee.setEmployeeId(generateEmployeeId());
        Employee emp = employeeRepository.save(convertToEntity(employee));
        return convertToBo(emp);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void deleteEmployeeById(String empId) {
        Employee emp = employeeRepository.findByEmployeeId(empId).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with emp id " + empId));
        employeeRepository.delete(emp);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public List<EmployeeBO> searchEmployeeByFirstName(String searchTerm) {
        return employeeRepository.searchEmployee(buildLikePattern(searchTerm)).stream().map(this::convertToBo).collect(Collectors.toList());
    }

    @Override
    public EmployeeBO updateEmployee(String employeeId, EmployeeBO employeeBO) {
        return employeeRepository.findByEmployeeId(employeeId).map(employee -> {
            employee.setEmail(employeeBO.getEmail());
            employee.setFirstName(employeeBO.getFirstName());
            employee.setLastName(employeeBO.getLastName());
            return convertToBo(employeeRepository.save(employee));
        }).orElseThrow(() -> new EmployeeNotFoundException("employee with id " + employeeId + " not found"));

    }

    private EmployeeBO convertToBo(Employee emp) {
        EmployeeBO employeeBO = new EmployeeBO();
        employeeBO.setEmployeeId(emp.getEmployeeId());
        employeeBO.setEmail(emp.getEmail());
        employeeBO.setFirstName(emp.getFirstName());
        employeeBO.setLastName(emp.getLastName());
        return employeeBO;
    }

    private Employee convertToEntity(EmployeeBO employeeBO) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeBO.getEmployeeId());
        employee.setFirstName(employeeBO.getFirstName());
        employee.setLastName(employeeBO.getLastName());
        employee.setEmail(employeeBO.getEmail());
        return employee;
    }

    private String generateEmployeeId() {
        Random r = new Random(System.currentTimeMillis());
        return "EMP-" + 10000 + r.nextInt(20000);
    }

    private String buildLikePattern(String searchTerm) {
        return searchTerm.toLowerCase() + "%";
    }
}
