package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.service.EducationService;
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
import java.util.Objects;

public class SubmitRequest implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer universityId = Integer.valueOf(req.getParameter(RequestParameters.UNIVERSITY_ID));
        Integer specialtyId = Integer.valueOf(req.getParameter(RequestParameters.SPECIALTY_ID));
        User user = (User) req.getSession().getAttribute("user");

        EducationService service = ServiceFactory.getInstance().getEducationService();
        Integer rating = service.getRatingByRequiredSubjects(user.getId(), specialtyId);
        if (Objects.isNull(rating)) {
            Specialty notAvailableSpecialty = service.getSpecialty(specialtyId);
            List<Subject> requiredSubjects = service.getRequiredSubjects(specialtyId);
            List<Specialty> specialties = service.getSpecialties(universityId);
            req.setAttribute("notAvailableSpecialty", notAvailableSpecialty);
            req.setAttribute("requiredSubjects", requiredSubjects);
            req.setAttribute("specialties", specialties);
            return Path.SPECIALTY_SELECTION_PAGE;
        } else {
            Specialty chosenSpecialty = service.submitRequest(user.getId(), rating, universityId, specialtyId);
            req.setAttribute("chosenSpecialty", chosenSpecialty);
            return Path.UNIVERSITY_SELECTION_PAGE;
        }
    }
}
