package ua.company.training.kukuruza.command.impl;

import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.entity.Exam;
import ua.company.training.kukuruza.entity.User;
import ua.company.training.kukuruza.command.ICommand;
import ua.company.training.kukuruza.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExamHandler implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute(AttributeNames.USER);

        Map<Integer, String> subjectIdToSubjectName = ServiceFactory.getExamService().getSubjectIdToSubjectName();
        List<Exam> userExams = ServiceFactory.getExamService().getUserExams(user.getId());
        List<Exam> availableExams = ServiceFactory.getExamService().getAvailableExams(userExams);

        req.setAttribute(AttributeNames.SUBJECT_ID_TO_SUBJECT_NAME, subjectIdToSubjectName);
        req.setAttribute(AttributeNames.USER_EXAMS, userExams);
        req.setAttribute(AttributeNames.AVAILABLE_EXAMS, availableExams);

        return Path.EXAM_PAGE;
    }
}
