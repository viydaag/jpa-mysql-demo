package spring.vaadin.jpa_mysql_demo.data;

import java.io.Serializable;

@FunctionalInterface
public interface Factory<E> extends Serializable {
    
    E create();
    
}
