package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.service.EducationService;
import ua.company.training.kukuruza.controller.service.ServiceException;
import ua.company.training.kukuruza.controller.util.AttributeNames;
import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.controller.util.RequestParameters;
import ua.company.training.kukuruza.model.entity.Specialty;
import ua.company.training.kukuruza.model.entity.Subject;
import ua.company.training.kukuruza.model.entity.User;
import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SubmitRequest implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer universityId = Integer.valueOf(req.getParameter(RequestParameters.UNIVERSITY_ID));
        Integer specialtyId = Integer.valueOf(req.getParameter(RequestParameters.SPECIALTY_ID));
        User user = (User) req.getSession().getAttribute(AttributeNames.USER);

        EducationService service = ServiceFactory.getInstance().getEducationService();
        try {
            Integer rating = service.getRatingByRequiredSubjects(user.getId(), specialtyId);
            Specialty chosenSpecialty = service.submitRequest(user.getId(), rating, universityId, specialtyId);
            req.setAttribute(AttributeNames.CHOSEN_SPECIALTY, chosenSpecialty);
            return Path.UNIVERSITY_SELECTION_PAGE;
        } catch (ServiceException e) {
            Specialty notAvailableSpecialty = service.getSpecialty(specialtyId);
            List<Subject> requiredSubjects = service.getRequiredSubjects(specialtyId);
            List<Specialty> specialties = service.getSpecialties(universityId);
            req.setAttribute(AttributeNames.NOT_AVAILABLE_SPECIALTY, notAvailableSpecialty);
            req.setAttribute(AttributeNames.REQUIRED_SUBJECTS, requiredSubjects);
            req.setAttribute(AttributeNames.SPECIALTIES, specialties);
            return Path.SPECIALTY_SELECTION_PAGE;
        }
    }
}
