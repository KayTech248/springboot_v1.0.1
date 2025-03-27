package com._iretechprojects.springboot.tutorial.repository;

import static org.junit.jupiter.api.Assertions.*;

import com._iretechprojects.springboot.tutorial.entity.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;


@DataJpaTest
class DepartmentRepositoryTest {

  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private TestEntityManager testEntityManager;



  @BeforeEach
  void setUp() {
    Department department = Department.builder().
        departmentName("HR").
        departmentCode("HR-001").
        departmentAddress("joburg").build();

    testEntityManager.persist(department);
  }

  @Test
  @DisplayName("Test to see if findbyId works")
  public void whenValidDepartmentId_then_returnDepartment(){
    long id = 1L;

    //Becuase it is an Optional
    Department department = departmentRepository.findById(id).get();
    assertEquals(department.getDepartmentName(), "HR");
  }

  @AfterEach
  void tearDown() {

  }
}