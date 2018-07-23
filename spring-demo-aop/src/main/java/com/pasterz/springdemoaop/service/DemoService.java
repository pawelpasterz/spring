package com.pasterz.springdemoaop.service;

import com.pasterz.springdemoaop.dao.Person;
import com.pasterz.springdemoaop.dao.Student;
import org.springframework.stereotype.Service;

@Service
public class DemoService implements ServiceInterface {

  @Override
  public Person createPerson(String name) {

    Person person = new Person();
    person.setName(name);

    return person;
  }

  @Override
  public Person makePerson(String name) {

    Person person = new Person();
    person.setName(new StringBuilder(name).reverse().toString());

    return person;
  }

  @Override
  public Person[] createRealPersona(String name) {

    Person[] realPerson = new Person[2];
    for (Person person : realPerson) {
      person.setName(name.toLowerCase());
    }

    return realPerson;
  }

  @Override
  public Student createStudent(String firstName, String lastName) {

    Student student = new Student();

    setStudentFirstName(firstName, student);
    setStudentLastName(lastName, student);

    student.doNothing();

    return student;
  }

  @Override
  public String doSomethingFunny() {
    return "<h2>Something Funny</h2>";
  }

  @Override
  public void setStudentLastName(String lastName, Student student) {
    student.setLastName(lastName);
  }

  @Override
  public void setStudentFirstName(String firstName, Student student) {
    student.setFirstName(firstName);
  }
}
