package ua.company.training.kukuruza.persistence.dao;

import ua.company.training.kukuruza.persistence.entity.Specialty;

import java.util.List;
import java.util.Set;

public interface IDaoSpecialty extends IDaoGeneric<Specialty, Integer> {
    List<Specialty> findByIdSet(Set<Integer> specialtiesId);
}
