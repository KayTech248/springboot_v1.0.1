package com._iretechprojects.springboot.tutorial.repository;

import com._iretechprojects.springboot.tutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

  public Department findByDepartmentName(String departmentName);

  public Department findByDepartmentNameIgnoreCase(String departmentName);

  @Query("SELECT d FROM Department d WHERE d.departmentName = ?1 and d.departmentAddress = ?2")
  public Department findByDepartmentNameCustom(String departmentName, String departmentAddress);

}
