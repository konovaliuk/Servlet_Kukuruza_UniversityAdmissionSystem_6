package ua.epam.training.kukuruza.model.dao;

import ua.epam.training.kukuruza.model.entity.Specialty;

import java.util.List;
import java.util.Set;

public interface IDaoSpecialty extends IDaoGeneric<Specialty, Integer> {
    List<Specialty> getByIdSet(Set<Integer> specialtiesId);
}
