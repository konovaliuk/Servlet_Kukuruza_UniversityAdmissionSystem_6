package ua.epam.training.kukuruza.controller.command.impl;

import ua.epam.training.kukuruza.controller.command.ICommand;
import ua.epam.training.kukuruza.controller.service.ServiceFactory;
import ua.epam.training.kukuruza.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class Results implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        Map<String, Integer> userGrades = ServiceFactory.getInstance().getResultService().getUserGrades(user.getId());
        req.setAttribute("userGrades", userGrades);
        return "/WEB-INF/jsp/user/results.jsp";
    }
}
