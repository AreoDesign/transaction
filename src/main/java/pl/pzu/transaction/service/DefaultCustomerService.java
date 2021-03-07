package pl.pzu.transaction.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.pzu.transaction.domain.Customer;
import pl.pzu.transaction.domain.CustomerDTO;
import pl.pzu.transaction.repository.CustomerRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultCustomerService implements CustomerService, PersistenceCatchable<Customer> {

    private final CustomerRepository customerRepository;
    private final AntiFraudService antiFraudService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addCustomer(CustomerDTO customerDTO) {
        log.info("executing method addCustomer under class: {}", this.getClass().getSimpleName());
        try {
            antiFraudService.verifyAndStore(customerDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveWithTryCatchBlock(customerDTO.toCustomerEntity());
        log.info("successfully stored customer in database.");
        return true;
    }

    @Override
    public void saveToWrap(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Collection<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
