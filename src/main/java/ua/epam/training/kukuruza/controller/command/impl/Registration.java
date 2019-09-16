package ua.epam.training.kukuruza.controller.command.impl;

import ua.epam.training.kukuruza.controller.command.ICommand;
import ua.epam.training.kukuruza.controller.service.ServiceException;
import ua.epam.training.kukuruza.controller.service.ServiceFactory;
import ua.epam.training.kukuruza.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String firstName = req.getParameter("first_name");
        String secondName = req.getParameter("second_name");
        String passportCode = req.getParameter("passport_code");
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        User newUser = new User.Builder()
                .setLogin(login)
                .setFirstName(firstName)
                .setSecondName(secondName)
                .setPassportCode(passportCode)
                .setGender(gender)
                .setEmail(email)
                .setPhone(phone)
                .setPassword(password)
                .build();

        try {
            ServiceFactory.getInstance().getRegistrationService().register(newUser);
            req.getSession().setAttribute("user", newUser);
            return "/index.jsp";
        } catch (ServiceException e) {
            req.setAttribute("registrationError", e.getMessage());
            return "/registration.jsp";
        }
    }
}
