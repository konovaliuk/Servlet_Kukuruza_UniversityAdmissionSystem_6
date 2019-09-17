package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.model.entity.Exam;
import ua.company.training.kukuruza.model.entity.User;
import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ExamService;
import ua.company.training.kukuruza.controller.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExamHandler implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        ExamService service = ServiceFactory.getInstance().getExamService();

        Map<Integer, String> subjectIdToSubjectName = service.getSubjectIdToSubjectName();
        List<Exam> userExams = service.getUserExams(user);
        List<Exam> availableExams = service.getAvailableExams(userExams);

        req.setAttribute("subjectIdToSubjectName", subjectIdToSubjectName);
        req.setAttribute("userExams", userExams);
        req.setAttribute("availableExams", availableExams);

        return Path.EXAM_PAGE;
    }
}
