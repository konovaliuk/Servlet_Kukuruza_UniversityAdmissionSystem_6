package ua.company.training.kukuruza.model.dao;

import ua.company.training.kukuruza.model.entity.SpecialtySubject;

import java.util.Set;

public interface IDaoSpecialtySubject extends IDaoGeneric<SpecialtySubject, Long> {
    Set<Integer> getSubjectsIdBySpecialtyId(Integer specialtyId);
}
