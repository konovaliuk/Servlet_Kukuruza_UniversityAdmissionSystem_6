package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.util.AttributeNames;
import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.controller.util.RequestParameters;
import ua.company.training.kukuruza.model.entity.User;
import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceFactory;

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
