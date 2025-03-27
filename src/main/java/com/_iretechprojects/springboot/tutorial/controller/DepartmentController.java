package com._iretechprojects.springboot.tutorial.controller;

import com._iretechprojects.springboot.tutorial.entity.Department;
import com._iretechprojects.springboot.tutorial.error.DepartmentNotFoundException;
import com._iretechprojects.springboot.tutorial.service.DepartmentService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

 // @Autowired
 private DepartmentService departmentService;

 private Logger logger = LoggerFactory.getLogger(DepartmentController.class);

  @Autowired
  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  //POST method to save departments
  @PostMapping("/departments")
  public Department addDepartment(@Valid @RequestBody Department department){
    logger.info("Executing Add department POST API");
    return departmentService.addDepartment(department);

  }

  //GET all the data from the Database
 @GetMapping("/departments")
 public List<Department> fetchDepartmentList(){
   logger.info("Executing Fetch Departments Get API");
   return departmentService.fetchDepartmentList();

 }

 //GET one Department by ID
 @GetMapping("/departments/{id}")
 public  Department fetchDepartmentById(@PathVariable("id") Long departmentId)
     throws DepartmentNotFoundException {
   logger.info("Executing fetch departmentby ID GET API");
   return departmentService.fetchDepartmentById(departmentId);

 }

 //DELETE Department by ID
 @DeleteMapping("/departments/{id}")
 public String deleteDepartmentById( @PathVariable("id") Long departmentId){
   logger.info("Executing Delete department by ID DELETE API");
   departmentService.deleteDepartmentById(departmentId);
   return "Department Deleted Successfully";

 }

 //UPDATE(PUT) Department by ID
 @PutMapping("/departments/{id}")
 public Department updateDepartmentById(@PathVariable("id") Long departmentId, @RequestBody Department department){
   logger.info("Executing Update department PUT API");
   return departmentService.updateDepartmentById(departmentId, department);
 }

 //Fetch Department By Name
  @GetMapping("/departments/names/{name}")
  public Department fetchDepartmentByName( @PathVariable("name") String departmentName){
    logger.info("Executing fetch department by name GET API");

    return departmentService.fetchDepartmentByName(departmentName);

  }

}
