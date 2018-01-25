package spring.vaadin.jpa_mysql_demo.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class CustomJpaRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements CustomJpaRepository<T, ID> {

    private final EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void refresh(T entity) {
        entityManager.refresh(entity);
    }

    @Override
    @Transactional
    public List<T> findByAttributeContainsText(String attributeName, String text) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cQuery = builder.createQuery(getDomainClass());
        Root<T> root = cQuery.from(getDomainClass());
        cQuery.select(root).where(builder.like(builder.lower(root.<String> get(attributeName)), "%" + text.toLowerCase() + "%"));

        TypedQuery<T> query = entityManager.createQuery(cQuery);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Page<T> findByAttributeContainsText(String attributeName, String text, Pageable page) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cQuery = builder.createQuery(getDomainClass());
        Root<T> root = cQuery.from(getDomainClass());
        cQuery.select(root).where(builder.like(builder.lower(root.<String> get(attributeName)), "%" + text.toLowerCase() + "%"));

        //        List<Order> orders = new ArrayList<Order>(2);
        //        orders.add(cb.asc(iRoot.get("name")));
        //        orders.add(cb.asc(iRoot.get("desc")));
        //
        List<Order> orders = new ArrayList<Order>();
        page.getSort().forEach(o -> orders.add(fromSpringOrder(builder, root, o)));
        cQuery.orderBy(orders);
        
        TypedQuery<T> query = entityManager.createQuery(cQuery);

        int totalRows = query.getResultList().size();

        query.setFirstResult(page.getOffset());
        query.setMaxResults(page.getPageSize());

        Page<T> result = new PageImpl<T>(query.getResultList(), page, totalRows);
        return result;
    }

    private Order fromSpringOrder(CriteriaBuilder builder, Root<T> root, Sort.Order springOrder) {
        if (springOrder.isDescending()) {
            return builder.desc(root.get(springOrder.getProperty()));
        }
        return builder.asc(root.get(springOrder.getProperty()));
    }

}
