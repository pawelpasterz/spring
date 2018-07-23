package com.pasterz.springdemoaop.aspect;

import com.pasterz.springdemoaop.dao.Person;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

  @Before("execution(* *Person(String)) && args(name)")
  public void beforeCreation(String name) {

    LOGGER.info("A request was issued for a sample name: " + name);
  }

  @Around("execution(* *Person(String)) && args(name)")
  public Object aroundCreation(ProceedingJoinPoint proceedingJoinPoint, String name) throws Throwable {

    LOGGER.info("A request was issued for a sample name: " + name);

    name = name + "!";

    Person person = (Person) proceedingJoinPoint.proceed(new Object[] {name});
    person.setName(person.getName().toUpperCase());

    return person;
  }

  @Around("execution(* createRealPersona(String)) && args(name)")
  public Object[] beforeCreateRealPersona(String name)
      throws Throwable {

    Person[] realPerson = new Person[2];
    realPerson[0] = new Person();
    realPerson[1] = new Person();
    realPerson[0].setName(name.toLowerCase() + "!!!!!");
    realPerson[1].setName(name.toUpperCase());

    return realPerson;
  }

  @Pointcut("execution(* createStudent(..))")
  private void forDaoPackage() {}

  @Before("forDaoPackage()")
  public void beforeStudentMethod() {

    System.out.println("TESTING");
  }

  @Pointcut("execution(* com.pasterz.springdemoaop.*.*.set*(..))")
  private void ifSetterIsCalled() {}

  @Before("execution(* com.pasterz.springdemoaop..set*(..))")
  public void whenStudentSetterUsed() {

    System.out.println("Student's setter used!");
  }
}
