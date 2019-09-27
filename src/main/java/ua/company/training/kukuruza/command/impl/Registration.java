package ua.company.training.kukuruza.command.impl;

import ua.company.training.kukuruza.service.ServiceFactory;
import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.entity.User;
import ua.company.training.kukuruza.command.ICommand;
import ua.company.training.kukuruza.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.company.training.kukuruza.util.RequestParameters.*;

public class Registration implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
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
            ServiceFactory.getInstance().getRegistrationService().register(newUser);
            req.getSession().setAttribute(AttributeNames.USER, newUser);
            return Path.INDEX_PAGE;
        } catch (ServiceException e) {
            req.setAttribute(AttributeNames.REGISTRATION_ERROR, e.getMessage());
            return Path.REGISTRATION_PAGE;
        }
    }
}
