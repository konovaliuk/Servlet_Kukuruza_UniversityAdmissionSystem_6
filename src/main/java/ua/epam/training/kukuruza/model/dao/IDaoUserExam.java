package ua.epam.training.kukuruza.model.dao;

import ua.epam.training.kukuruza.model.entity.UserExam;

import java.util.List;
import java.util.Set;

public interface IDaoUserExam extends IDaoGeneric<UserExam, Long> {
    void delete(List<UserExam> userExams);
    Set<Integer> getExamsIdByUserId(Long userId);
    void save(List<UserExam> userExams);
}
