package spring.vaadin.jpa_mysql_demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

    @Test
    public void givenStudents_whenFindByLastNamePageable_thenOk() {
        PageRequest pageRequest = new PageRequest(0, 2, new Sort(Direction.ASC, "firstName"));
        Page<Customer> page = repository.findByAttributeContainsText("lastName", "Bauer", pageRequest);
        List<Customer> customers = page.getContent();

        assertEquals("size incorrect", 2, customers.size());

        assertEquals("Alex", customers.get(0).getFirstName());
        assertEquals("Jack", customers.get(1).getFirstName());
        //        assertEquals("Kim", customers.get(2).getFirstName());

        pageRequest = new PageRequest(0, 3, new Sort(Direction.DESC, "firstName"));
        page = repository.findByAttributeContainsText("lastName", "Bauer", pageRequest);
        customers = page.getContent();

        assertEquals("size incorrect", 3, customers.size());

        assertEquals("Kim", customers.get(0).getFirstName());
        assertEquals("Jack", customers.get(1).getFirstName());
        assertEquals("Alex", customers.get(2).getFirstName());
    }

}
