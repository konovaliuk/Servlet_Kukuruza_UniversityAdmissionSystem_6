package ua.company.training.kukuruza.persistence.dao;

import ua.company.training.kukuruza.persistence.entity.Exam;

import java.util.List;
import java.util.Set;

public interface IDaoExam extends IDaoGeneric<Exam, Integer> {
    List<Exam> findByIdSet(Set<Integer> examsId);

    List<Exam> findNotInIdSet(Set<Integer> examsId);
}
