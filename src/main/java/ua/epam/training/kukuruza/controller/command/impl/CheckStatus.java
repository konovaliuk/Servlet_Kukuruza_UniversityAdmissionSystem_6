package ua.epam.training.kukuruza.controller.command.impl;

import ua.epam.training.kukuruza.controller.command.ICommand;
import ua.epam.training.kukuruza.controller.service.ServiceFactory;
import ua.epam.training.kukuruza.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckStatus implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        User updatedUser = ServiceFactory.getInstance().getCheckStatusService().getUpdatedUser(user);
        req.getSession().setAttribute("user", updatedUser);
        return "/WEB-INF/jsp/user/checkStatus.jsp";
    }
}
