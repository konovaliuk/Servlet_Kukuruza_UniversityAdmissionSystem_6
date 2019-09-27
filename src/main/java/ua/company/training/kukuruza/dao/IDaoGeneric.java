package ua.company.training.kukuruza.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IDaoGeneric<T, PK extends Serializable> {
    Optional<T> find(PK id);

    List<T> findAll();

    PK save(T entity);

    void update(T entity);

    boolean delete(PK id);
}
