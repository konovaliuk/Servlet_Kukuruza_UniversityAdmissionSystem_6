package ua.company.training.kukuruza.service;

import ua.company.training.kukuruza.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.entity.Grade;
import ua.company.training.kukuruza.entity.Subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultService {
    private AbstractDaoFactory factory;

    public ResultService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public Map<String, Integer> getUserGrades(Long userId) {
        Map<String, Integer> subjectNameToResult = new HashMap<>();

        List<Grade> userGrades = factory.getDaoGrade().findByUserId(userId);
        if (userGrades.isEmpty()) {
            return subjectNameToResult;
        }

        Map<Integer, String> subjectIdToSubjectName = factory.getDaoSubject().findAll()
                .stream()
                .collect(Collectors.toMap(Subject::getId, Subject::getName));
        for (Grade grade : userGrades) {
            subjectNameToResult.put(subjectIdToSubjectName.get(grade.getSubjectId()), grade.getResult());
        }

        return subjectNameToResult;
    }
}
