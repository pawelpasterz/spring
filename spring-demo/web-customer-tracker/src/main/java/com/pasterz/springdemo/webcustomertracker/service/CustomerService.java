package com.pasterz.springdemo.webcustomertracker.service;

import com.pasterz.springdemo.webcustomertracker.dao.CustomerDataAccessObject;
import com.pasterz.springdemo.webcustomertracker.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService implements CustomerServiceInterface {

  private CustomerDataAccessObject customerDataAccessObject;

  @Autowired
  public CustomerService(CustomerDataAccessObject customerDataAccessObject) {
    this.customerDataAccessObject = customerDataAccessObject;
  }

  @Override
  @Transactional
  public List<Customer> getCustomers() {
    List<Customer> customers = new ArrayList<>();
    customerDataAccessObject.findAllByOrderByLastNameAscFirstNameAsc().forEach(customers::add);
    return customers;
  }

  @Override
  @Transactional
  public void saveCustomer(Customer customer) {
    customerDataAccessObject.save(customer);
  }

  @Override
  @Transactional
  public Optional<Customer> getCustomer(int id) {
    return customerDataAccessObject.findById(id);
  }

  @Override
  @Transactional
  public void deleteCustomer(int id) {
    customerDataAccessObject.deleteById(id);
  }

  @Override
  @Transactional
  public List<Customer> getCustomerByFirstName(String firstName) {
    List<Customer> customers = new ArrayList<>();
    customerDataAccessObject.findByFirstName(firstName).forEach(customers::add);
    return customers;
  }
}
