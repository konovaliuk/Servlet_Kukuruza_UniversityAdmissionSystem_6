package ua.company.training.kukuruza.controller.service;

import ua.company.training.kukuruza.model.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.model.entity.Grade;
import ua.company.training.kukuruza.model.entity.Subject;

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
        Map<String, Integer> subjectNameToGrade = new HashMap<>();

        List<Grade> userGrades = factory.getDaoGrade().getUserGrades(userId);
        if (userGrades.isEmpty()) {
            return subjectNameToGrade;
        }

        Map<Integer, String> subjectIdToSubjectName = factory.getDaoSubject().getAll()
                .stream()
                .collect(Collectors.toMap(Subject::getId, Subject::getName));
        for (Grade grade : userGrades) {
            subjectNameToGrade.put(subjectIdToSubjectName.get(grade.getSubjectId()), grade.getGrade());
        }

        return subjectNameToGrade;
    }
}
