package ua.company.training.kukuruza.command.impl;

import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.util.RequestParameters;
import ua.company.training.kukuruza.entity.User;
import ua.company.training.kukuruza.command.ICommand;
import ua.company.training.kukuruza.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class ExamRegistration implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String[] examsId = req.getParameterValues(RequestParameters.EXAMS_ID);
        if (Objects.nonNull(examsId)) {
            User user = (User) req.getSession().getAttribute(AttributeNames.USER);
            ServiceFactory.getInstance().getExamService().registerUserToExams(user.getId(), examsId);
        }
        return Path.EXAM_COMMAND;
    }
}
