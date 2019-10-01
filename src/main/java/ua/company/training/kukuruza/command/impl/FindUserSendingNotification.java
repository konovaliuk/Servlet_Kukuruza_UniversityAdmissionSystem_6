package ua.company.training.kukuruza.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.util.RequestParameters;
import ua.company.training.kukuruza.entity.User;
import ua.company.training.kukuruza.command.ICommand;
import ua.company.training.kukuruza.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindUserSendingNotification implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(FindUserSendingNotification.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("Try to find users and for sending a notification");
        String firstName = req.getParameter(RequestParameters.FIRST_NAME);
        String secondName = req.getParameter(RequestParameters.SECOND_NAME);

        List<User> users = ServiceFactory.getInstance().getAdminService().findUsers(firstName, secondName);
        if (users.isEmpty()) {
            LOGGER.info("There aren't any available users");
            req.setAttribute(AttributeNames.FIRST_NAME, firstName);
            req.setAttribute(AttributeNames.SECOND_NAME, secondName);
        } else {
            LOGGER.info("Users were successfully found");
            req.setAttribute(AttributeNames.USERS, users);
        }

        return Path.SEND_NOTIFICATION_PAGE;
    }
}
