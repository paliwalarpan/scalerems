package com.scaler.ems.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.ems.exception.EmployeeNotFoundException;
import com.scaler.ems.model.EmployeeBO;
import com.scaler.ems.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetEmployee_success() throws Exception {
        String emp_id = "EMP-12345";
        EmployeeBO employeeBO = new EmployeeBO();
        employeeBO.setFirstName("Tony");
        employeeBO.setLastName("Stark");
        employeeBO.setEmployeeId(emp_id);
        employeeBO.setEmail("tony.stark@mail.com");
        Mockito.when(employeeService.fetchEmployee(emp_id)).thenReturn(employeeBO);

        MvcResult mvcResult = mockMvc.perform(get("/employee/{id}", "EMP-12345")).andExpect(status().isOk()).andReturn();
        assertEquals(objectMapper.writeValueAsString(employeeBO), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetEmployee_employeeNotFound() throws Exception {
        String emp_id = "EMP-12345";
        EmployeeBO employeeBO = new EmployeeBO();
        employeeBO.setFirstName("Tony");
        employeeBO.setLastName("Stark");
        employeeBO.setEmployeeId(emp_id);
        employeeBO.setEmail("tony.stark@mail.com");
        Mockito.when(employeeService.fetchEmployee(emp_id)).thenThrow(EmployeeNotFoundException.class);
        mockMvc.perform(get("/employee/{id}", "EMP-12345")).andExpect(status().is(404));
    }

    @Test
    void testUpdateEmployee_success() throws Exception {
        String emp_id = "EMP-12345";
        EmployeeBO employeeBO = new EmployeeBO();
        employeeBO.setFirstName("Tony");
        employeeBO.setLastName("Stark");
        employeeBO.setEmail("tony.stark@mail.com");

        Mockito.when(employeeService.updateEmployee(emp_id, employeeBO)).thenReturn(employeeBO);
        MvcResult mvcResult = mockMvc.perform(put("/employee/{id}", "EMP-12345").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employeeBO))).andExpect(status().is(200)).andReturn();
        assertEquals(objectMapper.writeValueAsString(employeeBO), mvcResult.getResponse().getContentAsString());

    }


}