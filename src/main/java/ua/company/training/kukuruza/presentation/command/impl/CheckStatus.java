package ua.company.training.kukuruza.presentation.command.impl;

import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.persistence.entity.User;
import ua.company.training.kukuruza.presentation.command.ICommand;
import ua.company.training.kukuruza.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckStatus implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute(AttributeNames.USER);
        User updatedUser = ServiceFactory.getCheckStatusService().getUpdatedUser(user.getId());
        req.getSession().setAttribute(AttributeNames.USER, updatedUser);
        return Path.CHECK_STATUS_PAGE;
    }
}
