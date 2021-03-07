package pl.pzu.transaction.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.Validate;

@Builder
@EqualsAndHashCode
@ToString
public class CustomerDTO {

    private final Firstname firstname;
    private final Surname surname;

    public CustomerDTO(Firstname firstname, Surname surname) {
        Validate.notNull(firstname);
        Validate.notNull(surname);
        this.firstname = firstname;
        this.surname = surname;
    }

    public Customer toCustomerEntity() {
        return Customer.builder()
                .firstname(firstname.getAsString())
                .surname(surname.getAsString())
                .build();
    }

    public CustomerFraud toCustomerFraudEntity() {
        return CustomerFraud.builder()
                .firstname(firstname.getAsString())
                .surname(surname.getAsString())
                .build();
    }

    public String getFirstnameAsString() {
        return firstname.getAsString();
    }

    public String getSurnameAsString() {
        return surname.getAsString();
    }
}
