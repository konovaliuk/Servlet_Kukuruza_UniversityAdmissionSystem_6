package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceFactory;
import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.controller.util.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetGrade implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long userId = Long.valueOf(req.getParameter(RequestParameters.USER_ID));
        Integer subjectId = Integer.valueOf(req.getParameter(RequestParameters.SUBJECT_ID));
        Integer grade = Integer.valueOf(req.getParameter(RequestParameters.GRADE));

        ServiceFactory.getInstance().getAdminService().setGrade(userId, subjectId, grade);
        req.setAttribute("success", "success");

        return Path.SET_GRADE_PAGE;
    }
}
