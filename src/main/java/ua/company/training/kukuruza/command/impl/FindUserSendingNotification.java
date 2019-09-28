package ua.company.training.kukuruza.command.impl;

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
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String firstName = req.getParameter(RequestParameters.FIRST_NAME);
        String secondName = req.getParameter(RequestParameters.SECOND_NAME);

        List<User> users = ServiceFactory.getInstance().getAdminService().findUsers(firstName, secondName);
        if (users.isEmpty()) {
            req.setAttribute(AttributeNames.FIRST_NAME, firstName);
            req.setAttribute(AttributeNames.SECOND_NAME, secondName);
        } else {
            req.setAttribute(AttributeNames.USERS, users);
        }

        return Path.SEND_NOTIFICATION_PAGE;
    }
}