package com.scaler.ems.controller;

import com.scaler.ems.model.EmployeeBO;
import com.scaler.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{id}")
    public EmployeeBO getMyDetails(@PathVariable("id") String empId) {
        return employeeService.fetchEmployee(empId);
    }

    @PutMapping(value = "/{id}")
    public EmployeeBO updateDetails(@PathVariable("id") String empId, @RequestBody EmployeeBO newDetails) {
        return employeeService.updateEmployee(empId, newDetails);
    }
}
