package ua.epam.training.kukuruza.model.dao;

import ua.epam.training.kukuruza.model.entity.SpecialtySubject;

import java.util.Set;

public interface IDaoSpecialtySubject extends IDaoGeneric<SpecialtySubject, Long> {
    Set<Integer> getSubjectsIdBySpecialtyId(Integer specialtyId);
}
