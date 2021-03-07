package pl.pzu.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pzu.transaction.domain.CustomerFraud;

@Repository
public interface CustomerFraudRepository extends JpaRepository<CustomerFraud, Integer> {
}
