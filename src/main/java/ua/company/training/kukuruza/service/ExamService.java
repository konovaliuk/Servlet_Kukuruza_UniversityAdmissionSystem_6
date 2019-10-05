package ua.company.training.kukuruza.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.entity.Exam;
import ua.company.training.kukuruza.entity.Subject;
import ua.company.training.kukuruza.entity.UserExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ExamService {
    private final static Logger LOGGER = LogManager.getLogger(ExamService.class);
    private AbstractDaoFactory factory;

    ExamService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public List<Exam> getUserExams(Long userId) {
        LOGGER.info("Try to get a list of user exams");
        Set<Integer> userExamsId = factory.getDaoUserExam().findExamsIdByUserId(userId);
        return factory.getDaoExam().findByIdSet(userExamsId);
    }

    public List<Exam> getAvailableExams(List<Exam> userExams) {
        LOGGER.info("Try to get a list of user available exams");
        Set<Integer> examsId = userExams.stream()
                .map(Exam::getId)
                .collect(Collectors.toSet());
        return factory.getDaoExam().findNotInIdSet(examsId);
    }

    public Map<Integer, String> getSubjectIdToSubjectName() {
        LOGGER.info("Try to get a map of subjectId to subjectName");
        List<Subject> subjects = factory.getDaoSubject().findAll();
        return subjects.stream().collect(Collectors.toMap(Subject::getId, Subject::getName));
    }


    public void registerUserToExams(Long userId, String[] examsId) {
        LOGGER.info("Try to register user to exams");
        if (examsId.length > 0) {
            List<UserExam> userExams = getUserExams(userId, examsId);
            factory.getDaoUserExam().save(userExams);
        }
    }

    public void cancelUserRegistrationToExams(Long userId, String[] examsId) {
        LOGGER.info("Try to cancel user registration to exams");
        if (examsId.length > 0) {
            List<UserExam> userExams = getUserExams(userId, examsId);
            factory.getDaoUserExam().delete(userExams);
        }
    }

    private List<UserExam> getUserExams(Long userId, String[] examsId) {
        List<UserExam> userExams = new ArrayList<>();
        for (String id : examsId) {
            Integer examId = Integer.valueOf(id);
            UserExam userExam = new UserExam.Builder()
                    .setUserId(userId)
                    .setExamId(examId)
                    .build();
            userExams.add(userExam);
        }
        return userExams;
    }
}
