package spring.vaadin.jpa_mysql_demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.vaadin.jpa_mysql_demo.data.Customer;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CustomerRepository
        extends CustomJpaRepository<Customer, Long> {

	List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}
