package com._iretechprojects.springboot.tutorial.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

  private HttpStatus statusCode;
  private String message;
}
