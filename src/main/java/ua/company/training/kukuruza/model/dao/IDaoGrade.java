package ua.company.training.kukuruza.model.dao;

import ua.company.training.kukuruza.model.entity.Grade;

import java.util.List;
import java.util.Optional;

public interface IDaoGrade extends IDaoGeneric<Grade, Long> {
    List<Grade> findByUserId(Long userId);

    Optional<Grade> findByUserIdAndSubjectId(Long userId, Integer subjectId);
}
