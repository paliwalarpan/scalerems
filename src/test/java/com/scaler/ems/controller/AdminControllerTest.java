package com.scaler.ems.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AdminController.class)
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_create_employee() throws Exception {
        EmployeeBO employeeBO = new EmployeeBO();
        employeeBO.setFirstName("Tony");
        employeeBO.setLastName("Stark");
        employeeBO.setEmail("tony.stark@mail.com");

        Mockito.when(employeeService.saveEmployee(employeeBO)).thenReturn(employeeBO);
        MvcResult mvcResult = mockMvc.perform(post("/admin/employee/").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employeeBO))).andExpect(status().is(201)).andReturn();
        assertEquals(objectMapper.writeValueAsString(employeeBO), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void test_delete_employee() throws Exception {
        String empToDelete = "EMP-12345";

        Mockito.doNothing().when(employeeService).deleteEmployeeById(empToDelete);
        MvcResult mvcResult = mockMvc.perform(delete("/admin/employee/{id}", empToDelete).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(204)).andReturn();
    }
}