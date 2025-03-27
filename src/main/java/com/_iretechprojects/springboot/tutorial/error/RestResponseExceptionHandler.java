package com._iretechprojects.springboot.tutorial.error;

import com._iretechprojects.springboot.tutorial.controller.DepartmentController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
This class was created to handle exceptions thrown by all the controllers in this particular application.
It does do so by inheriting from the ResponseEntityExceptionHandler class and using the @RestControllerAdvice to mark it as
a class to handle all exceptions thrown by the controllers.
It contains a method of type ResponseEntity that handles the error thrown by the DepartmentNotFoundException class. It also takes in
two parameters. the DepartmentNotFoundException object and the WebRequest object which can contain details of the request sent.
this method is also annotated as an exception handler for the DepartmentNotFoundException class.
it returns a response entity status accompanied with an http status code and exception message
 */



@RestControllerAdvice
@ResponseStatus
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

  private Logger logger = LoggerFactory.getLogger(RestResponseExceptionHandler.class);

  @ExceptionHandler(DepartmentNotFoundException.class)
  public ResponseEntity <ErrorMessage> departmentNotFoundException(DepartmentNotFoundException exception, WebRequest request){
    ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
    logger.info(String.valueOf(request));

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
  }

}
