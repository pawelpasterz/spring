package pl.pasterz.crmspring.service;

import java.util.List;

import pl.pasterz.crmspring.entity.Customer;

public interface CustomerService {

	List<Customer> getCustomers();
	void saveCustomer(Customer theCustomer);
	Customer getCustomer(int theId);
	void deleteCustomer(int theId);
	
}
