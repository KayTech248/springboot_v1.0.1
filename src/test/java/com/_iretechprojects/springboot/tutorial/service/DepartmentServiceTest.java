package com._iretechprojects.springboot.tutorial.service;

import static org.junit.jupiter.api.Assertions.*;

import com._iretechprojects.springboot.tutorial.entity.Department;
import com._iretechprojects.springboot.tutorial.error.DepartmentNotFoundException;
import com._iretechprojects.springboot.tutorial.repository.DepartmentRepository;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.bind.annotation.DeleteMapping;

@SpringBootTest
class DepartmentServiceTest {

  @Autowired
  DepartmentService departmentService;

  @MockitoBean
  DepartmentRepository departmentRepository;

  @BeforeEach
  void setUp() {
    //Create mock objects
    Department department = Department.builder().
        departmentId(1L).
        departmentName("IT").
        departmentCode("IT-001").departmentAddress("Johannesburg").build();
    //Mockito into action
    Mockito.when(departmentRepository.
        findByDepartmentNameCustom("IT", "Johannesburg")).
        thenReturn(department);

    //Mockito into action for ID
    Mockito.when(departmentRepository.findById(1L)).
        thenReturn(Optional.of(department));

  }

  @AfterEach
  void tearDown() {

  }

  @Test
  @DisplayName("Get Data based on valid Department name")
  public void whenValidDepartmentName_thenDepartmentShouldBeFound(){
    String departmentName = "IT";
    Department found = departmentService.fetchDepartmentByName(departmentName);
    assertEquals(departmentName, found.getDepartmentName());
  }

  @Test
  @DisplayName("Get Data based on valid Department ID")
  public void whenValidDepartmentId_thenDepartmentExists() throws DepartmentNotFoundException {
    Long id = 1L;
    Department found = departmentService.fetchDepartmentById(id);
    assertEquals(id, found.getDepartmentId());
  }
  @Test
  @DisplayName("Get Exception with invalid Department ID")
  @Disabled
  public void whenInvalidDepartmentById_thenThrowException() {
    Mockito.when(departmentRepository.findById(2L))
        .thenThrow(new DepartmentNotFoundException("Department with ID 2 not found"));

    assertThrows(DepartmentNotFoundException.class, () -> {
      departmentRepository.findById(2L);
    });
  }
}