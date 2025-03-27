package com._iretechprojects.springboot.tutorial.service;

import com._iretechprojects.springboot.tutorial.entity.Department;
import com._iretechprojects.springboot.tutorial.error.DepartmentNotFoundException;
import java.util.List;

public interface DepartmentService {

  public Department addDepartment(Department department);

  public List<Department> fetchDepartmentList();

  public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

  void deleteDepartmentById(Long departmentId);

  public Department updateDepartmentById(Long departmentId, Department department);

  public Department fetchDepartmentByName(String departmentName);
}
