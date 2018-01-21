package spring.vaadin.jpa_mysql_demo;

import java.io.Serializable;

public interface Entity extends Cloneable, Serializable {

    public Long getId();

    public void setId(Long id);

}
