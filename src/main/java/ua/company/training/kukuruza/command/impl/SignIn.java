package ua.company.training.kukuruza.command.impl;

import ua.company.training.kukuruza.service.ServiceFactory;
import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.util.RequestParameters;
import ua.company.training.kukuruza.entity.User;
import ua.company.training.kukuruza.command.ICommand;
import ua.company.training.kukuruza.service.ServiceException;
import ua.company.training.kukuruza.util.UserType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignIn implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter(RequestParameters.LOGIN);
        String password = req.getParameter(RequestParameters.PASSWORD);

        try {
            User user = ServiceFactory.getInstance().getAuthenticationService().signIn(login, password);
            req.getSession().setAttribute(AttributeNames.USER, user);

            if (user.getUserTypeId() == UserType.STUDENT.getId()) {
                return Path.INDEX_PAGE;
            }
            if (user.getUserTypeId() == UserType.ADMIN.getId()) {
                return "redirect:" + Path.ADMIN_PAGE;
            }

            throw new RuntimeException("Wrong UserTypeId");
        } catch (ServiceException e) {
            req.setAttribute(AttributeNames.SIGN_IN_ERROR, e.getMessage());
            return Path.SIGN_IN_PAGE;
        }
    }
}
