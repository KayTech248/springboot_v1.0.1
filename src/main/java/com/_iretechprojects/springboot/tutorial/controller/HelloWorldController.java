package com._iretechprojects.springboot.tutorial.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @Value("${HelloWorld.message}")
  private String message;
  @Value("${HelloWorld.greeting}")
  private String greeting;
  //Constructor
/*
  public HelloWorldController() {
    greeting = "Hello World!! yessir";
  }

 */
  //@RequestMapping(value = "/", method = RequestMethod.GET)
  @GetMapping(path ="/")
  public String helloworld() {
    return this.message + this.greeting;
  }

}



