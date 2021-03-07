package pl.pzu.transaction.service;

import pl.pzu.transaction.domain.Customer;
import pl.pzu.transaction.domain.CustomerDTO;

import java.util.Collection;

public interface CustomerService {
    Collection<Customer> getCustomers();

    boolean addCustomer(CustomerDTO customer);
}
