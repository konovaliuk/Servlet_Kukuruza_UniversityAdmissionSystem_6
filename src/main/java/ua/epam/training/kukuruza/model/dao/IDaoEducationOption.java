package ua.epam.training.kukuruza.model.dao;

import ua.epam.training.kukuruza.model.entity.EducationOption;

import java.util.Optional;
import java.util.Set;

public interface IDaoEducationOption extends IDaoGeneric<EducationOption, Long> {
    Set<Integer> getSpecialtiesIdByUniversityId(Integer universityId);
    Optional<EducationOption> getByUniversityIdAndSpecialtyId(Integer universityId, Integer specialtyId);
}
