package ua.company.training.kukuruza.model.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IDaoGeneric<T, PK extends Serializable> {
    Optional<T> get(PK id);

    List<T> getAll();

    PK save(T entity);

    void update(T entity);

    boolean delete(PK id);
}
