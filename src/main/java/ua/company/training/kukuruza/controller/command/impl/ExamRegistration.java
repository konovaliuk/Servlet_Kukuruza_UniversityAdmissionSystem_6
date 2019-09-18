package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.controller.util.RequestParameters;
import ua.company.training.kukuruza.model.entity.User;
import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class ExamRegistration implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String[] examsId = req.getParameterValues(RequestParameters.EXAMS_ID);
        if (Objects.nonNull(examsId)) {
            User user = (User) req.getSession().getAttribute("user");
            ServiceFactory.getInstance().getExamService().registerUserToExams(user.getId(), examsId);
        }
        return Path.EXAM_COMMAND;
    }
}
