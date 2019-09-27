package ua.company.training.kukuruza.dao;

import ua.company.training.kukuruza.entity.Specialty;

import java.util.List;
import java.util.Set;

public interface IDaoSpecialty extends IDaoGeneric<Specialty, Integer> {
    List<Specialty> findByIdSet(Set<Integer> specialtiesId);
}
