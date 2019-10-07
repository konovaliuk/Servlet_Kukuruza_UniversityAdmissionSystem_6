package ua.company.training.kukuruza.presentation.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.service.ServiceFactory;
import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.util.RequestParameters;
import ua.company.training.kukuruza.persistence.entity.User;
import ua.company.training.kukuruza.presentation.command.ICommand;
import ua.company.training.kukuruza.service.ServiceException;
import ua.company.training.kukuruza.util.UserType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignIn implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(SignIn.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("Start of sign in process");
        String login = req.getParameter(RequestParameters.LOGIN);
        String password = req.getParameter(RequestParameters.PASSWORD);

        try {
            User user = ServiceFactory.getAuthenticationService().signIn(login, password);
            req.getSession().setAttribute(AttributeNames.USER, user);
            LOGGER.info("User successfully signed in");
            if (user.getUserTypeId() == UserType.STUDENT.getId())
                return Path.INDEX_PAGE;

            if (user.getUserTypeId() == UserType.ADMIN.getId())
                return "redirect:" + Path.ADMIN_PAGE;

            throw new RuntimeException("Wrong UserTypeId");
        } catch (ServiceException e) {
            LOGGER.info("User fail sign in", e);
            req.setAttribute(AttributeNames.SIGN_IN_ERROR, e.getMessage());
            return Path.SIGN_IN_PAGE;
        }
    }
}
