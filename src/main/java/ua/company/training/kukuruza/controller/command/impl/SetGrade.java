package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetGrade implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        Integer subjectId = Integer.valueOf(req.getParameter("subject_id"));
        Integer grade = Integer.valueOf(req.getParameter("grade"));

        ServiceFactory.getInstance().getAdminService().setGrade(userId, subjectId, grade);
        req.setAttribute("success", "success");

        return "/admin/setGrade.jsp";
    }
}
