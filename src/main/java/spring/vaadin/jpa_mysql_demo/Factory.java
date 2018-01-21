package spring.vaadin.jpa_mysql_demo;

import java.io.Serializable;

@FunctionalInterface
public interface Factory<E> extends Serializable {
    
    E create();
    
}
