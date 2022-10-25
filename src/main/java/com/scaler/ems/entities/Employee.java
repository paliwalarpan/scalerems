package com.scaler.ems.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Employee {

    private String employeeId;

    public Employee(String employeeId) {
        this.employeeId = employeeId;
    }
}
