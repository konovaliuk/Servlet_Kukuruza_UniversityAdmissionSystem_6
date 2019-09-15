package ua.epam.training.kukuruza.model.dao;

import ua.epam.training.kukuruza.model.entity.Grade;

import java.util.List;
import java.util.Optional;

public interface IDaoGrade extends IDaoGeneric<Grade, Long> {
    List<Grade> getUserGrades(Long userId);
    Optional<Grade> getByUserIdAndSubjectId(Long userId, Integer subjectId);
}
