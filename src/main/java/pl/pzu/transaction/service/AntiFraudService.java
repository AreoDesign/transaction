package pl.pzu.transaction.service;

import pl.pzu.transaction.domain.CustomerDTO;
import pl.pzu.transaction.domain.CustomerFraud;

import java.util.Collection;

public interface AntiFraudService {
    void verifyAndStore(CustomerDTO customerDTO) throws RuntimeException;

    Collection<CustomerFraud> getFraudCustomers();
}
