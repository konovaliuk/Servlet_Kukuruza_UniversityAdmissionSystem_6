package ua.company.training.kukuruza.dao;

import ua.company.training.kukuruza.entity.SpecialtySubject;

import java.util.Set;

public interface IDaoSpecialtySubject extends IDaoGeneric<SpecialtySubject, Long> {
    Set<Integer> findSubjectsIdBySpecialtyId(Integer specialtyId);
}
