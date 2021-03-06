package spring.vaadin.jpa_mysql_demo.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import spring.vaadin.jpa_mysql_demo.data.Entity;
import spring.vaadin.jpa_mysql_demo.data.Factory;
import spring.vaadin.jpa_mysql_demo.repository.CustomJpaRepository;

public interface DataService<E extends Entity, K extends Serializable> {

    /**
     * 
     * @return the entity created
     */
    E create();

    /**
     * 
     * @param entity
     *            the entity to create
     */
    void create(E entity);

    /**
     *
     * @param entity
     *            the entity to delete
     */
    void delete(E entity);

    /**
     *
     * @param key
     *            the key of the entity to delete
     */
    void delete(K key);

    /**
     * 
     * @param entitySet
     */
    void delete(Collection<E> entitySet);

    /**
     *
     * @return all instances of the entity
     */
    List<E> findAll();

    List<E> findByAttributeContainsText(String attributeName, String text);

    Page<E> findByAttributeContainsText(String attributeName, String text, Pageable page);

    
    /**
     *
     * @param key
     *            the key of the entity to read
     * @return the entity read
     */
    E getOne(K key);

    /**
     * Refresh the state of the instance from the database, 
     * overwriting changes made to the entity, if any. 
     * @param entity
     */
    void refresh(E entity);

    /**
     *
     * @param entity
     *            the entity to update
     * @return the entity updated
     */
    E save(E entity);

    /**
     * 
     * @param entitySet
     * @return
     */
    Collection<E> saveOrUpdate(Collection<E> entitySet);

    /**
     *
     * @param dao
     *            the DAO to set
     */
    void setRepository(CustomJpaRepository<E, K> dao);

    /**
     *
     * @param factory
     *            the factory to set
     */
    void setEntityFactory(Factory<E> factory);

    /**
     *
     * @param entity
     *            the entity to update
     * @return the entity updated
     */
    E update(E entity);


    /**
     * 
     * @return the entity count
     */
    long count();


}
