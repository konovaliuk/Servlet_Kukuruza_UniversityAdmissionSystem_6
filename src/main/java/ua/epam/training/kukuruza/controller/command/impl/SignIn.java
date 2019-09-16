package ua.epam.training.kukuruza.controller.command.impl;

import ua.epam.training.kukuruza.controller.command.ICommand;
import ua.epam.training.kukuruza.controller.service.ServiceException;
import ua.epam.training.kukuruza.controller.service.ServiceFactory;
import ua.epam.training.kukuruza.controller.util.UserType;
import ua.epam.training.kukuruza.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignIn implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = ServiceFactory.getInstance().getAuthenticationService().signIn(login, password);
            req.getSession().setAttribute("user", user);

            if (user.getUserTypeId() == UserType.STUDENT.getId()) {
                return "/index.jsp";
            }

            throw new RuntimeException("Wrong UserTypeId");
        } catch (ServiceException e) {
            req.setAttribute("signInError", e.getMessage());
            return "/signIn.jsp";
        }
    }
}
