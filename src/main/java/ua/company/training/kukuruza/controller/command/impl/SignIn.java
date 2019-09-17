package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.service.ServiceFactory;
import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.model.entity.User;
import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceException;
import ua.company.training.kukuruza.controller.util.UserType;

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
                return Path.INDEX_PAGE;
            }
            if (user.getUserTypeId() == UserType.ADMIN.getId()) {
                return "redirect:" + Path.ADMIN_PAGE;
            }

            throw new RuntimeException("Wrong UserTypeId");
        } catch (ServiceException e) {
            req.setAttribute("signInError", e.getMessage());
            return Path.SIGN_IN_PAGE;
        }
    }
}
