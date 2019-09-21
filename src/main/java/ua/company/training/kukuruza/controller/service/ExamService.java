package ua.company.training.kukuruza.controller.service;

import ua.company.training.kukuruza.model.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.model.entity.Exam;
import ua.company.training.kukuruza.model.entity.Subject;
import ua.company.training.kukuruza.model.entity.User;
import ua.company.training.kukuruza.model.entity.UserExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ExamService {
    private AbstractDaoFactory factory;

    public ExamService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public List<Exam> getUserExams(Long userId) {
        Set<Integer> userExamsId = factory.getDaoUserExam().findExamsIdByUserId(userId);
        return factory.getDaoExam().findByIdSet(userExamsId);
    }

    public List<Exam> getAvailableExams(List<Exam> userExams) {
        Set<Integer> examsId = userExams.stream()
                .map(Exam::getId)
                .collect(Collectors.toSet());
        return factory.getDaoExam().findNotInIdSet(examsId);
    }

    public Map<Integer, String> getSubjectIdToSubjectName() {
        List<Subject> subjects = factory.getDaoSubject().findAll();
        return subjects.stream().collect(Collectors.toMap(Subject::getId, Subject::getName));
    }


    public void registerUserToExams(Long userId, String[] examsId) {
        List<UserExam> userExams = getUserExams(userId, examsId);
        factory.getDaoUserExam().save(userExams);
    }

    public void cancelRegistrationUserToExams(Long userId, String[] examsId) {
        List<UserExam> userExams = getUserExams(userId, examsId);
        factory.getDaoUserExam().delete(userExams);
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
