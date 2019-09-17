package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.model.entity.Specialty;
import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SpecialtySelection implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer universityId = Integer.valueOf(req.getParameter("universityId"));
        List<Specialty> specialties = ServiceFactory.getInstance().getEducationService().getSpecialties(universityId);
        req.setAttribute("specialties", specialties);
        return "/WEB-INF/jsp/user/specialtySelection.jsp";
    }
}
