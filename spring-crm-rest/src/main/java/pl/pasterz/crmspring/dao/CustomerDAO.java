package pl.pasterz.crmspring.dao;

import java.util.List;

import pl.pasterz.crmspring.entity.Customer;

public interface CustomerDAO {

	List<Customer> getCustomers();
	void saveCustomer(Customer theCustomer);
	Customer getCustomer(int theId);
	void deleteCustomer(int theId);
}
