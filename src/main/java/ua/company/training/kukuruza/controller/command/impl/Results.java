package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.model.entity.User;
import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class Results implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        Map<String, Integer> userGrades = ServiceFactory.getInstance().getResultService().getUserGrades(user.getId());
        req.setAttribute("userGrades", userGrades);
        return Path.RESULTS_PAGE;
    }
}
