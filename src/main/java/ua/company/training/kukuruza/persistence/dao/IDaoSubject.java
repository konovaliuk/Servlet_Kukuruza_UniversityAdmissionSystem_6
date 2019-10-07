package ua.company.training.kukuruza.persistence.dao;

import ua.company.training.kukuruza.persistence.entity.Subject;

import java.util.List;
import java.util.Set;

public interface IDaoSubject extends IDaoGeneric<Subject, Integer> {
    List<Subject> findByIdSet(Set<Integer> subjectsId);
}
