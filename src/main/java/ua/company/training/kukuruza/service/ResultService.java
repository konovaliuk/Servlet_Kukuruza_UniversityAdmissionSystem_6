package ua.company.training.kukuruza.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.entity.Grade;
import ua.company.training.kukuruza.entity.Subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultService {
    private static final Logger LOGGER = LogManager.getLogger(ResultService.class);
    private AbstractDaoFactory factory;

    ResultService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public Map<String, Integer> getUserGrades(Long userId) {
        LOGGER.info("Try to get user grades");
        Map<String, Integer> subjectNameToResult = new HashMap<>();

        List<Grade> userGrades = factory.getDaoGrade().findByUserId(userId);
        if (userGrades.isEmpty()) {
            LOGGER.info("User doesn't have any grades");
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
