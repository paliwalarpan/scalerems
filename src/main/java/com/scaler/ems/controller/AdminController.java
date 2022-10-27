package com.scaler.ems.controller;

import com.scaler.ems.model.EmployeeBO;
import com.scaler.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/employee")
public class AdminController {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeBO createNew(@RequestBody EmployeeBO employeeBO) {
        return employeeService.saveEmployee(employeeBO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") String empId) {
        employeeService.deleteEmployeeById(empId);
    }

    @GetMapping("/search")
    public List<EmployeeBO> search(@RequestParam("firstNameStartsWith") String firstNameStartsWith) {
        return employeeService.searchEmployeeByFirstName(firstNameStartsWith);
    }
}
