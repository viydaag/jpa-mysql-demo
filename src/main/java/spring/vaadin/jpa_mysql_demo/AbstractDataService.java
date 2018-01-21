package spring.vaadin.jpa_mysql_demo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractDataService<E extends Entity, K extends Serializable> implements DataService<E, K>, Serializable {

    private static final long serialVersionUID = 4655778098973992981L;
    
    protected CustomJpaRepository<E, K> entityRepository;
    protected Factory<E> entityFactory;

    public AbstractDataService() {
        super();
    }

    /**
     *
     * @return the entity created
     */
    @Override
    public E create() {
        return entityFactory.create();
    }

    /**
     *
     * @param entity
     *            the entity to create
     */
    @Override
    public void create(E entity) {
        entityRepository.save(entity);
    }

    /**
     *
     * @param entity
     *            the entity to delete
     */
    @Override
    public void delete(E entity) {
        entityRepository.delete(entity);
    }

    @Override
    public void delete(K key) {
        entityRepository.delete(key);
    }

    @Override
    public void delete(Collection<E> entitySet) {
        entityRepository.delete(entitySet);
    }
    
    @Override
    public void refresh(E entity) {
        entityRepository.refresh(entity);
    }

    @Override
    public List<E> findAll() {
        return entityRepository.findAll();
    }


    /**
     *
     * @param key
     *            the key of the entity to read
     * @return the entity read
     */
    @Override
    public E getOne(K key) {
        return entityRepository.getOne(key);
    }

    /**
     * 
     * @param entity
     * @return
     */
    @Override
    public E save(E entity) {
        return entityRepository.save(entity);
    }

    /**
     * 
     * @param entitySet
     * @return
     */
    @Override
    public Collection<E> saveOrUpdate(Collection<E> entitySet) {
        return entityRepository.save(entitySet);
    }

    /**
     *
     * @param repository
     *            the entity repository to set
     */
    @Override
    public void setRepository(CustomJpaRepository<E, K> repository) {
        this.entityRepository = repository;
    }

    /**
     *
     * @param entityFactory
     *            the entity factory to set
     */
    @Override
    public void setEntityFactory(Factory<E> entityFactory) {
        this.entityFactory = entityFactory;
    }

    /**
     *
     * @param entity
     *            the entity to update
     * @return the entity updated
     */
    @Override
    public E update(E entity) {
        return entityRepository.save(entity);
    }

    @Override
    public long count() {
        return entityRepository.count();
    }



}
