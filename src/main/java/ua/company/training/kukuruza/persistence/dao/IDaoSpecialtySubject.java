package ua.company.training.kukuruza.persistence.dao;

import ua.company.training.kukuruza.persistence.entity.SpecialtySubject;

import java.util.Set;

public interface IDaoSpecialtySubject extends IDaoGeneric<SpecialtySubject, Long> {
    Set<Integer> findSubjectsIdBySpecialtyId(Integer specialtyId);
}
