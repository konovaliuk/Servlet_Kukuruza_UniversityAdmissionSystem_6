package ua.company.training.kukuruza.persistence.dao;

import ua.company.training.kukuruza.persistence.entity.University;

import java.util.List;

public interface IDaoUniversity extends IDaoGeneric<University, Integer> {
    Long getRowsCount();

    List<University> findAll(Integer skip, Integer limit);
}
