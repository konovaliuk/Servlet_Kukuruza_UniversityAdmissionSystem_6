package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.model.entity.User;
import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckStatus implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        User updatedUser = ServiceFactory.getInstance().getCheckStatusService().getUpdatedUser(user);
        req.getSession().setAttribute("user", updatedUser);
        return Path.CHECK_STATUS_PAGE;
    }
}
