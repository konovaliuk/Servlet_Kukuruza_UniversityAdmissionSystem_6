package ua.company.training.kukuruza.dao;

import ua.company.training.kukuruza.entity.University;

import java.util.List;

public interface IDaoUniversity extends IDaoGeneric<University, Integer> {
    Long getRowsCount();

    List<University> findAll(Integer skip, Integer limit);
}
