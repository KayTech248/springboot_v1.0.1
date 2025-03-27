package com._iretechprojects.springboot.tutorial.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long departmentId;
  @NotBlank(message = "Please Add Department Name")
  /*
  @Size(max=3, min = 0)

  @Future
  @Past
  @PastOrPresent
  @Email(regexp = ".*")
  @Length(max = 10, message = "Please enter a valid size etc")
   */
  private String departmentName;
  private String departmentCode;
  private String departmentAddress;
}
