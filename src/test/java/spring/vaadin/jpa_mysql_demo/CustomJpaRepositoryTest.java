package spring.vaadin.jpa_mysql_demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import spring.vaadin.jpa_mysql_demo.data.Customer;
import spring.vaadin.jpa_mysql_demo.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class CustomJpaRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    //    @Before
    //    public void setup() {
    //        Customer student = new Customer("Jack", "Bauer");
    //        repository.save(student);
    //        Customer student2 = new Customer("Chloe", "O'Brian");
    //        repository.save(student2);
    //        Customer student3 = new Customer("Kim", "Bauer");
    //        repository.save(student3);
    //    }

    @Test
    public void givenStudents_whenFindByFirstName_thenOk() {
        List<Customer> customers = repository.findByAttributeContainsText("firstName", "Kim");

        assertEquals("size incorrect", 1, customers.size());
    }

}
