package ua.company.training.kukuruza.presentation.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.service.ServiceFactory;
import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.persistence.entity.User;
import ua.company.training.kukuruza.presentation.command.ICommand;
import ua.company.training.kukuruza.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.company.training.kukuruza.util.RequestParameters.*;

public class Registration implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(Registration.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("Start of new user registration");
        User newUser = new User.Builder()
                .setLogin(req.getParameter(LOGIN))
                .setFirstName(req.getParameter(FIRST_NAME))
                .setSecondName(req.getParameter(SECOND_NAME))
                .setPassportCode(req.getParameter(PASSPORT_CODE))
                .setGender(req.getParameter(GENDER))
                .setEmail(req.getParameter(EMAIL))
                .setPhone(req.getParameter(PHONE))
                .setPassword(req.getParameter(PASSWORD))
                .build();

        try {
            ServiceFactory.getRegistrationService().register(newUser);
            req.getSession().setAttribute(AttributeNames.USER, newUser);
            LOGGER.info("User was successfully register");
            return Path.INDEX_PAGE;
        } catch (ServiceException e) {
            LOGGER.info("User registration fail", e);
            req.setAttribute(AttributeNames.REGISTRATION_ERROR, e.getMessage());
            return Path.REGISTRATION_PAGE;
        }
    }
}
