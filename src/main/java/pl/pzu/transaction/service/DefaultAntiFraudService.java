package pl.pzu.transaction.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.pzu.transaction.domain.CustomerDTO;
import pl.pzu.transaction.domain.CustomerFraud;
import pl.pzu.transaction.repository.CustomerFraudRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultAntiFraudService implements AntiFraudService, PersistenceCatchable<CustomerFraud> {

    private final CustomerFraudRepository customerFraudRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void verifyAndStore(CustomerDTO customerDTO) throws DataIntegrityViolationException {
        if ("Fraudowski".equalsIgnoreCase(customerDTO.getSurnameAsString())) {
            log.warn("Found fraud client! Customer: {}", customerDTO);
            throw new RuntimeException();
        }
        log.info("executing method verify under class: {}", this.getClass().getSimpleName());
        saveWithTryCatchBlock(customerDTO.toCustomerFraudEntity());
        log.info("successfully stored customer in database.");
    }

    @Override
    public Collection<CustomerFraud> getFraudCustomers() {
        return customerFraudRepository.findAll();
    }

    @Override
    public void saveToWrap(CustomerFraud customer) {
        customerFraudRepository.save(customer);
    }
}
