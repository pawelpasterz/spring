package com.pasterz.springdemoaop.controller;

import com.pasterz.springdemoaop.dao.Person;
import com.pasterz.springdemoaop.dao.Student;
import com.pasterz.springdemoaop.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private DemoService demoService;

  @Autowired
  public Controller(DemoService demoService) {
    this.demoService = demoService;
  }

  @RequestMapping("/sample")
  public Person person(String name) {
    return demoService.createPerson(name);
  }

  @RequestMapping("/makePerson")
  public Person makePerson(@RequestParam("name") String name) {
    return demoService.makePerson(name);
  }

  @RequestMapping("/createRealPersona")
  public Person[] createRealPersona(String name) {
    return new Person[2];
  }

  @RequestMapping("/createStudent")
  public Student createStudent(String firstName, String lastName) {
    Student student = demoService.createStudent(firstName, lastName);
    demoService.setStudentFirstName("JAS", student);
    return student;
  }

  @RequestMapping("/dosth")
  public String doSomethingFunny() {
    Person person = new Person("Funny Person");
    Student student = new Student("Funny", "Student");

    System.out.println("I call Person getter: " + person.getName());
    System.out.println(
        "I call Student getter: " + student.getLastName() + ", " + student.getFirstName());

    return demoService.doSomethingFunny();
  }
}
