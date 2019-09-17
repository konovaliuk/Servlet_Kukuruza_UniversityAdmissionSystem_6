package ua.epam.training.kukuruza.controller.service;

import ua.epam.training.kukuruza.model.dao.AbstractDaoFactory;
import ua.epam.training.kukuruza.model.entity.Grade;
import ua.epam.training.kukuruza.model.entity.Subject;
import ua.epam.training.kukuruza.model.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AdminService {
    private AbstractDaoFactory factory;

    public AdminService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public List<User> findUsers(String firstName, String secondName) {
        if (Objects.isNull(firstName) || Objects.isNull(secondName))
            return Collections.emptyList();
        return factory.getDaoUser().getByFirstNameAndSecondName(firstName, secondName);
    }

    public List<Subject> getSubjects() {
        return factory.getDaoSubject().getAll();
    }

    public void setGrade(Long userId, Integer subjectId, Integer grade) {
        Optional<Grade> userGrade = factory.getDaoGrade().getByUserIdAndSubjectId(userId, subjectId);
        if (userGrade.isPresent()) {
            Grade gradeUpdate = userGrade.get();
            gradeUpdate.setGrade(grade);
            factory.getDaoGrade().update(gradeUpdate);
        } else {
            Grade newGrade = new Grade.Builder()
                    .setUserId(userId)
                    .setSubjectId(subjectId)
                    .setGrade(grade)
                    .build();
            factory.getDaoGrade().save(newGrade);
        }
    }

    public void setUserStatus(Long userId, Integer userStatus) {
        Optional<User> user = factory.getDaoUser().get(userId);
        User u = user.orElseThrow(RuntimeException::new);
        u.setUserStatusId(userStatus);
        factory.getDaoUser().update(u);
    }
}
