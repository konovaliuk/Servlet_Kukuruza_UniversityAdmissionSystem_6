package ua.epam.training.kukuruza.model.dao;

import ua.epam.training.kukuruza.model.entity.Grade;

import java.util.List;

public interface IDaoGrade extends IDaoGeneric<Grade, Long> {
    List<Grade> getUserGrades(Long userId);
}
