package spring.vaadin.jpa_mysql_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.vaadin.jpa_mysql_demo.data.Customer;
import spring.vaadin.jpa_mysql_demo.repository.CustomerRepository;

@Service
public class CustomerService
        extends AbstractDataService<Customer, Long> {

    private static final long serialVersionUID = 2829557257740152303L;

    CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repo) {
        super();
        this.repository = repo;
        setRepository(repository);
        setEntityFactory(() -> new Customer());
    }
    
    public List<Customer> findByLastNameStartsWithIgnoreCase(String lastName) {
        return repository.findByLastNameStartsWithIgnoreCase(lastName);
    }

}
