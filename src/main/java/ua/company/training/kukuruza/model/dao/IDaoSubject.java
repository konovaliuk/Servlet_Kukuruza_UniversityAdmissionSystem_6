package ua.company.training.kukuruza.model.dao;

import ua.company.training.kukuruza.model.entity.Subject;

import java.util.List;
import java.util.Set;

public interface IDaoSubject extends IDaoGeneric<Subject, Integer> {
    List<Subject> findByIdSet(Set<Integer> subjectsId);
}
