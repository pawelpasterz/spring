package com.pasterz.springdemo.webcustomertracker.dao;

import com.pasterz.springdemo.webcustomertracker.entity.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDataAccessObject extends CrudRepository<Customer, Integer> {

  List<Customer> findAllByOrderByLastNameAsc();
  List<Customer> findAllByOrderByLastNameAscFirstNameAsc();
  List<Customer> findByFirstName(String firstName);
}
