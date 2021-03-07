package pl.pzu.transaction.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pzu.transaction.domain.Customer;
import pl.pzu.transaction.domain.CustomerDTO;
import pl.pzu.transaction.domain.CustomerFraud;
import pl.pzu.transaction.domain.Firstname;
import pl.pzu.transaction.domain.Surname;
import pl.pzu.transaction.service.AntiFraudService;
import pl.pzu.transaction.service.CustomerService;

import java.util.Collection;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final AntiFraudService antiFraudService;

    @GetMapping
    Collection<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/fraud")
    Collection<CustomerFraud> getFraudCustomers() {
        return antiFraudService.getFraudCustomers();
    }

    @PostMapping
    HttpStatus addCustomer(@RequestParam String name, @RequestParam String surname) {
        log.info("executing method addCustomer under class: {}", this.getClass().getSimpleName());
        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstname(Firstname.of(name))
                .surname(Surname.of(surname))
                .build();
        customerService.addCustomer(customerDTO);
        return HttpStatus.ACCEPTED;
    }
}
