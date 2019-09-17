package ua.epam.training.kukuruza.controller.command.impl;

import ua.epam.training.kukuruza.controller.command.ICommand;
import ua.epam.training.kukuruza.controller.service.ServiceFactory;
import ua.epam.training.kukuruza.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class ExamRegistration implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String[] examsId = req.getParameterValues("exam_id");
        if (Objects.nonNull(examsId)) {
            User user = (User) req.getSession().getAttribute("user");
            ServiceFactory.getInstance().getExamService().registerUserToExams(user.getId(), examsId);
        }
        return "/exam.do?command=exam";
    }
}
