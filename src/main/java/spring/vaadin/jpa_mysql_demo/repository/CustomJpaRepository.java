package spring.vaadin.jpa_mysql_demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomJpaRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID> {

    public void refresh(T entity);

    public List<T> findByAttributeContainsText(String attributeName, String text);

    public Page<T> findByAttributeContainsText(String attributeName, String text, Pageable page);

}
