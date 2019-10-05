package ua.company.training.kukuruza.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.entity.Grade;
import ua.company.training.kukuruza.entity.Subject;
import ua.company.training.kukuruza.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AdminService {
    private static final Logger LOGGER = LogManager.getLogger(AdminService.class);
    private AbstractDaoFactory factory;

    AdminService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public List<User> findUsers(String firstName, String secondName) {
        LOGGER.info("Try to find a list of users by firstName and secondName");
        if (Objects.isNull(firstName) || Objects.isNull(secondName))
            return Collections.emptyList();
        return factory.getDaoUser().findByFirstNameAndSecondName(firstName, secondName);
    }

    public List<Subject> getSubjects() {
        LOGGER.info("Try to get a list of all subjects");
        return factory.getDaoSubject().findAll();
    }

    public void setGrade(Long userId, Integer subjectId, Integer result) {
        LOGGER.info("Try to set a grade");
        Optional<Grade> userGrade = factory.getDaoGrade().findByUserIdAndSubjectId(userId, subjectId);
        if (userGrade.isPresent()) {
            Grade gradeUpdate = userGrade.get();
            gradeUpdate.setResult(result);
            LOGGER.info("Try to update a grade");
            factory.getDaoGrade().update(gradeUpdate);
        } else {
            Grade newGrade = new Grade.Builder()
                    .setUserId(userId)
                    .setSubjectId(subjectId)
                    .setResult(result)
                    .build();
            LOGGER.info("Try to save new grade");
            factory.getDaoGrade().save(newGrade);
        }
    }

    public void setUserStatus(Long userId, Integer userStatus) {
        LOGGER.info("Try to update user status");
        User user = factory.getDaoUser().find(userId).orElseThrow(RuntimeException::new);
        user.setUserStatusId(userStatus);
        factory.getDaoUser().update(user);
    }
}
