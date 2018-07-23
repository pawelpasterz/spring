package com.pasterz.springdemo.webcustomertracker.controller;

import com.pasterz.springdemo.webcustomertracker.entity.Customer;
import com.pasterz.springdemo.webcustomertracker.service.CustomerService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {

  private CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/list")
  public String listCustomers(Model model) {
    model.addAttribute("customers", customerService.getCustomers());
    return "list-customers";
  }

  @GetMapping("/showFormForAdd")
  public String showFormForAdd(Model model) {
    Customer customer = new Customer();
    model.addAttribute("customer", customer);
    return "customer-form";
  }

  @GetMapping("/showFormForUpdate")
  public String showFormForUpdate(@RequestParam("id") int id, Model model) {
    Optional<Customer> customer = customerService.getCustomer(id);
    model.addAttribute("customer", customer);
    return "customer-form";
  }

  @PostMapping("/saveCustomer")
  public String saveCustomer(@ModelAttribute("customer") Customer customer) {
    customerService.saveCustomer(customer);
    return "redirect:/customer/list";
  }

  @GetMapping("/delete")
  public String deleteCustomer(@RequestParam("id") int id) {
    customerService.deleteCustomer(id);
    return "redirect:/customer/list";
  }

  @PostMapping("/search")
  public String searchCustomersByFirstName(
      @RequestParam("firstNameToSearch") String firstName, Model model) {
    model.addAttribute("customers", customerService.getCustomerByFirstName(firstName));
    return "list-customers";
  }
}
