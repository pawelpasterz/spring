package com.pasterz.springdemo.webcustomertracker.service;

import com.pasterz.springdemo.webcustomertracker.entity.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterface {

  List<Customer> getCustomers();
  Optional<Customer> getCustomer(int Id);
  void saveCustomer(Customer customer);
  void deleteCustomer(int id);
  List<Customer> getCustomerByFirstName(String firstName);
}
