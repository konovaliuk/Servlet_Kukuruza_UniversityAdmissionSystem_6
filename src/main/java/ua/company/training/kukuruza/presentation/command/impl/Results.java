package ua.company.training.kukuruza.presentation.command.impl;

import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.persistence.entity.User;
import ua.company.training.kukuruza.presentation.command.ICommand;
import ua.company.training.kukuruza.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class Results implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute(AttributeNames.USER);
        Map<String, Integer> userGrades = ServiceFactory.getResultService().getUserGrades(user.getId());
        req.setAttribute(AttributeNames.USER_GRADES, userGrades);
        return Path.RESULTS_PAGE;
    }
}
