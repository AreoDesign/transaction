package pl.pzu.transaction.service;

import org.springframework.dao.DataIntegrityViolationException;

interface PersistenceCatchable<T> {

    org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PersistenceCatchable.class);

    void saveToWrap(T customer);

    default void saveWithTryCatchBlock(T customer) {
        try {
            saveToWrap(customer);
        } catch (IllegalArgumentException ex) {
            log.error("Caught IllegalArgumentException while trying to save customer: {}. Resulting: {}", customer, ex.getMessage());
            return;
        } catch (DataIntegrityViolationException ex) {
            log.error("Trying to save entity which exists already: {}. Resulting: {}", customer, ex.getMessage());
            return;
        } catch (RuntimeException ex) {
            log.error("Caught RuntimeException while trying to save customer: {}. Resulting: {}", customer, ex.getMessage());
            return;
        }
    }
}
