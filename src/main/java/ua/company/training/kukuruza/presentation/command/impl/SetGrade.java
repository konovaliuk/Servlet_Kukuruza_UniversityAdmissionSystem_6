package ua.company.training.kukuruza.presentation.command.impl;

import ua.company.training.kukuruza.presentation.command.ICommand;
import ua.company.training.kukuruza.service.ServiceFactory;
import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.util.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetGrade implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long userId = Long.valueOf(req.getParameter(RequestParameters.USER_ID));
        Integer subjectId = Integer.valueOf(req.getParameter(RequestParameters.SUBJECT_ID));
        Integer result = Integer.valueOf(req.getParameter(RequestParameters.RESULT));

        ServiceFactory.getAdminService().setGrade(userId, subjectId, result);
        req.setAttribute(AttributeNames.SUCCESS, "success");

        return Path.SET_GRADE_PAGE;
    }
}
