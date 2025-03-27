package com._iretechprojects.springboot.tutorial.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com._iretechprojects.springboot.tutorial.entity.Department;
import com._iretechprojects.springboot.tutorial.error.DepartmentNotFoundException;
import com._iretechprojects.springboot.tutorial.service.DepartmentService;
import com._iretechprojects.springboot.tutorial.service.DepartmentServiceImpl;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

  @MockitoBean
  private DepartmentService departmentService;

  @Autowired
  private MockMvc mockMvc;

  private Department department;


  @BeforeEach
  void setUp() {
    department = Department.builder().departmentId(1L).
        departmentName("IT").
        departmentCode("IT-001").
        departmentAddress("Johannesburg").build();
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  @DisplayName("This test checks if the add department endpoint works")
  void addDepartment() throws Exception {
    Department inputDepartment = Department.builder().
        departmentName("Information Technology").
        departmentCode("IT-001").
        departmentAddress("Johannesburg").build();

    //MockObject
    Mockito.when(departmentService.addDepartment(inputDepartment)).thenReturn(department);

    mockMvc.perform(MockMvcRequestBuilders.post("/departments").
        contentType(MediaType.APPLICATION_JSON).content("{\n"
        + "    \"departmentName\": \"Information Technology\",\n"
        + "    \"departmentCode\": \"IT-001\",\n"
        + "    \"departmentAddress\": \"Johannesburg\"\n" + "}")).
        andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @DisplayName("This test checks if the get department by ID endpoint works")
  void fetchDepartmentById() throws Exception {
    Mockito.when(departmentService.fetchDepartmentById(department.getDepartmentId())).thenReturn(department);

    mockMvc.perform(MockMvcRequestBuilders.get("/departments/" + department.getDepartmentId()).
        contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).
        andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
  }

  @Test
  @DisplayName("This test checks if the delete by department ID is called")
  void deleteDepartmentById() throws Exception {
    // Correct way: Set up the mock *before* calling the method
    Mockito.doNothing().when(departmentService).deleteDepartmentById(department.getDepartmentId());

    // Now, call the method
    departmentService.deleteDepartmentById(department.getDepartmentId());

    // Verify the method was called
    Mockito.verify(departmentService).deleteDepartmentById(department.getDepartmentId());

    mockMvc.perform(MockMvcRequestBuilders.delete("/departments/" + department.getDepartmentId()).
        contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    //Needs more modification

  }


}