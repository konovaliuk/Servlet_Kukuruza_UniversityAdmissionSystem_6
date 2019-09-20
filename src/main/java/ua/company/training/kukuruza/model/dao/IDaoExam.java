package ua.company.training.kukuruza.model.dao;

import ua.company.training.kukuruza.model.entity.Exam;

import java.util.List;
import java.util.Set;

public interface IDaoExam extends IDaoGeneric<Exam, Integer> {
    List<Exam> findByIdSet(Set<Integer> examsId);

    List<Exam> findNotInIdSet(Set<Integer> examsId);
}
