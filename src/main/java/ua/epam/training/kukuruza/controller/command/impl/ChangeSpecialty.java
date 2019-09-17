package ua.epam.training.kukuruza.controller.command.impl;

import ua.epam.training.kukuruza.controller.command.ICommand;
import ua.epam.training.kukuruza.controller.service.ServiceFactory;
import ua.epam.training.kukuruza.model.entity.University;
import ua.epam.training.kukuruza.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ChangeSpecialty implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        ServiceFactory.getInstance().getEducationService().dropRequest(user.getId());
        List<University> universities = ServiceFactory.getInstance().getEducationService().getUniversities();
        req.setAttribute("universities", universities);
        return "/WEB-INF/jsp/user/universitySelection.jsp";
    }
}
