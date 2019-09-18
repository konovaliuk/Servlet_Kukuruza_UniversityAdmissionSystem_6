package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.service.ServiceFactory;
import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.model.entity.User;
import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.company.training.kukuruza.controller.util.RequestParameters.*;

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
            req.getSession().setAttribute("user", newUser);
            return Path.INDEX_PAGE;
        } catch (ServiceException e) {
            req.setAttribute("registrationError", e.getMessage());
            return Path.REGISTRATION_PAGE;
        }
    }
}
