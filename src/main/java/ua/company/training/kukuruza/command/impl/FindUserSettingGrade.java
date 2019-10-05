package ua.company.training.kukuruza.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.util.RequestParameters;
import ua.company.training.kukuruza.entity.Subject;
import ua.company.training.kukuruza.entity.User;
import ua.company.training.kukuruza.command.ICommand;
import ua.company.training.kukuruza.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindUserSettingGrade implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(FindUserSettingGrade.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("Try to find users for setting a grade");
        String firstName = req.getParameter(RequestParameters.FIRST_NAME);
        String secondName = req.getParameter(RequestParameters.SECOND_NAME);

        List<User> users = ServiceFactory.getAdminService().findUsers(firstName, secondName);
        if (users.isEmpty()) {
            LOGGER.info("There aren't any available users");
            req.setAttribute(AttributeNames.FIRST_NAME, firstName);
            req.setAttribute(AttributeNames.SECOND_NAME, secondName);
        } else {
            LOGGER.info("Users were successfully found");
            List<Subject> subjects = ServiceFactory.getAdminService().getSubjects();
            req.setAttribute(AttributeNames.SUBJECTS, subjects);
            req.setAttribute(AttributeNames.USERS, users);
        }

        return Path.SET_GRADE_PAGE;
    }
}
