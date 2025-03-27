package com._iretechprojects.springboot.tutorial.service;

import com._iretechprojects.springboot.tutorial.entity.Department;
import com._iretechprojects.springboot.tutorial.error.DepartmentNotFoundException;
import com._iretechprojects.springboot.tutorial.repository.DepartmentRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements
    com._iretechprojects.springboot.tutorial.service.DepartmentService {

  private DepartmentRepository departmentRepository;

  @Autowired
  public DepartmentServiceImpl( DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public Department addDepartment(Department department) {
    return departmentRepository.save(department);
  }

  @Override
  public List<Department> fetchDepartmentList() {
    return departmentRepository.findAll();
  }

  @Override
  public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
    Optional<Department> department = departmentRepository.findById(departmentId);
    if(!department.isPresent()){
      String message = "Department with id " + departmentId  +  " does not exist";
      throw new DepartmentNotFoundException(message);
    }
    return departmentRepository.findById(departmentId).get();
  }

  @Override
  public void deleteDepartmentById(Long departmentId) {
    departmentRepository.deleteById(departmentId);
  }

  @Override
  public Department updateDepartmentById(Long departmentId, Department department) {
    //Store preliminary Object
    Department dept = departmentRepository.findById(departmentId).get();

    //Not empty name
    if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())){
      dept.setDepartmentName(department.getDepartmentName());
    }
    //Not empty code
    if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())){
      dept.setDepartmentCode(department.getDepartmentCode());
    }
    //Not empty address
    if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())){
      dept.setDepartmentAddress(department.getDepartmentAddress());
    }

    return departmentRepository.save(dept);
  }

  @Override
  public Department fetchDepartmentByName(String departmentName) {
    //return departmentRepository.findByDepartmentName(departmentName);
    //return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);

    //Try with JPQL
    String departmentAddress = "Johannesburg";
    return departmentRepository.findByDepartmentNameCustom(departmentName, departmentAddress);
  }
}
