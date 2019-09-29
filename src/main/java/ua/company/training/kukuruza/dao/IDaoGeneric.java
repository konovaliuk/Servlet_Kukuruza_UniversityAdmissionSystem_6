package ua.company.training.kukuruza.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Common interface that represents a generic data access object.
 *
 * @param <T>  type of an entity
 * @param <PK> primary key by which an entity can be retrieved
 * @author Andrii Kukuruza
 */
public interface IDaoGeneric<T, PK extends Serializable> {
    /**
     * The method allows to find an entity by a primary key.
     *
     * @param id PK
     * @return Optional of an entity
     */
    Optional<T> find(PK id);

    /**
     * The method allows to find all entities.
     *
     * @return List of entities
     */
    List<T> findAll();

    /**
     * The method allows to save an entity
     * and it returns auto generated primary key.
     *
     * @param entity T
     * @return Primary key
     */
    PK save(T entity);

    /**
     * The method allows to update an entity.
     *
     * @param entity T
     */
    void update(T entity);

    /**
     * The method allows to delete an entity by primary key.
     *
     * @param id PK
     * @return True, if an entity was found and successfully deleted or else - false
     */
    boolean delete(PK id);
}
