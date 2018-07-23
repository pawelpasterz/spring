package com.pasterz.springdemoaop.service;

import com.pasterz.springdemoaop.dao.Person;
import com.pasterz.springdemoaop.dao.Student;

public interface ServiceInterface {

  Person createPerson(String name);
  Person makePerson(String name);
  Person[] createRealPersona(String name);

  Student createStudent(String firstName, String lastName);
  void setStudentLastName(String lastName, Student student);
  void setStudentFirstName(String firstName, Student student);

  String doSomethingFunny();
}
