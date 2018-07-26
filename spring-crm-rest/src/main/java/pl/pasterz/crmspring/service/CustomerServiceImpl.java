package pl.pasterz.crmspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pasterz.crmspring.dao.CustomerDAO;
import pl.pasterz.crmspring.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

  private CustomerDAO customerDAO;

  @Autowired
  public CustomerServiceImpl(CustomerDAO customerDAO) {
    this.customerDAO = customerDAO;
  }

  @Override
  @Transactional
  public List<Customer> getCustomers() {
    return customerDAO.getCustomers();
  }

  @Override
  @Transactional
  public void saveCustomer(Customer theCustomer) {

    customerDAO.saveCustomer(theCustomer);
  }

  @Override
  @Transactional
  public Customer getCustomer(int theId) {

    return customerDAO.getCustomer(theId);
  }

  @Override
  @Transactional
  public void deleteCustomer(int theId) {

    customerDAO.deleteCustomer(theId);
  }
}
