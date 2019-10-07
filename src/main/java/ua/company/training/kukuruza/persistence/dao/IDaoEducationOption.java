package ua.company.training.kukuruza.persistence.dao;

import ua.company.training.kukuruza.persistence.entity.EducationOption;

import java.util.Optional;
import java.util.Set;

public interface IDaoEducationOption extends IDaoGeneric<EducationOption, Long> {
    Set<Integer> findSpecialtiesIdByUniversityId(Integer universityId, Integer skip, Integer limit);

    Optional<EducationOption> findByUniversityIdAndSpecialtyId(Integer universityId, Integer specialtyId);

    Long getSpecialtiesRowsCount(Integer universityId);
}
