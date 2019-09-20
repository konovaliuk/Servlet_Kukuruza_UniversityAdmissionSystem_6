package ua.company.training.kukuruza.model.dao;

import ua.company.training.kukuruza.model.entity.UserExam;

import java.util.List;
import java.util.Set;

public interface IDaoUserExam extends IDaoGeneric<UserExam, Long> {
    void delete(List<UserExam> userExams);

    Set<Integer> findExamsIdByUserId(Long userId);

    void save(List<UserExam> userExams);
}
