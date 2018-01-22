package spring.vaadin.jpa_mysql_demo.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer
        implements spring.vaadin.jpa_mysql_demo.data.Entity {

    private static final long serialVersionUID = -7313977137461057204L;

    @Id
	@GeneratedValue
	private Long id;

	private String firstName;

	private String lastName;

    public Customer() {
	}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
    public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id,
				firstName, lastName);
	}

    @Override
    public void setId(Long id) {
        this.id = id;

    }

}
