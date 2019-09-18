package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.util.AttributeNames;
import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.controller.util.RequestParameters;
import ua.company.training.kukuruza.model.entity.Specialty;
import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SpecialtySelection implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer universityId = Integer.valueOf(req.getParameter(RequestParameters.UNIVERSITY_ID));
        List<Specialty> specialties = ServiceFactory.getInstance().getEducationService().getSpecialties(universityId);
        req.setAttribute(AttributeNames.SPECIALTIES, specialties);
        return Path.SPECIALTY_SELECTION_PAGE;
    }
}
