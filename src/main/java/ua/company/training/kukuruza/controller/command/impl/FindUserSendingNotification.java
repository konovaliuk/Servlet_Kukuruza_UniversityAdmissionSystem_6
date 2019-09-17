package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.model.entity.User;
import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindUserSendingNotification implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String firstName = req.getParameter("first_name");
        String secondName = req.getParameter("second_name");

        List<User> users = ServiceFactory.getInstance().getAdminService().findUsers(firstName, secondName);
        if (users.isEmpty()) {
            req.setAttribute("firstName", firstName);
            req.setAttribute("secondName", secondName);
        } else {
            req.setAttribute("users", users);
        }

        return Path.SEND_NOTIFICATION_PAGE;
    }
}
