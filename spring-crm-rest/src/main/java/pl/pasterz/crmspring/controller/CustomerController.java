package pl.pasterz.crmspring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pasterz.crmspring.entity.Customer;
import pl.pasterz.crmspring.error.CustomerNotFoundException;
import pl.pasterz.crmspring.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

  private CustomerService service;

  @Autowired
  public CustomerController(CustomerService service) {
    this.service = service;
  }

  @GetMapping("/customers")
  public List<Customer> getCustomers() {
    return service.getCustomers();
  }

  @GetMapping("/customers/{id}")
  public Customer getCustomer(@PathVariable int id) {

    Customer customer = service.getCustomer(id);

    if (customer == null) {
      throw new CustomerNotFoundException("Customer id not found - " + id);
    }

    return customer;
  }

  @PostMapping("/customers")
  public Customer addCustomer(@RequestBody Customer customer) {
    customer.setId(0);

    service.saveCustomer(customer);

    return customer;
  }

  @PutMapping("/customers")
  public Customer updateCustomer(@RequestBody Customer customer) {

    service.saveCustomer(customer);

    return customer;
  }

  @DeleteMapping("/customers/{id}")
  public String deleteCustomer(@PathVariable int id) {

    Customer tempCustomer = service.getCustomer(id);

    if (tempCustomer == null) {
      throw new CustomerNotFoundException("Customer id not found - " + id);
    }

    service.deleteCustomer(id);

    return "Deleted customer with id - " + id;
  }
}
