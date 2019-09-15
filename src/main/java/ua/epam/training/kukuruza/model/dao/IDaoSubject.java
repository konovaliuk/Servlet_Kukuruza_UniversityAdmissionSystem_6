package ua.epam.training.kukuruza.model.dao;

import ua.epam.training.kukuruza.model.entity.Subject;

import java.util.List;
import java.util.Set;

public interface IDaoSubject extends IDaoGeneric<Subject, Integer> {
    List<Subject> getByIdSet(Set<Integer> subjectsId);
}
