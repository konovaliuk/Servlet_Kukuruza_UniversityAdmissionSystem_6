package ua.epam.training.kukuruza.controller.command.impl;

import ua.epam.training.kukuruza.controller.command.ICommand;
import ua.epam.training.kukuruza.controller.service.EducationService;
import ua.epam.training.kukuruza.controller.service.ServiceFactory;
import ua.epam.training.kukuruza.model.entity.Specialty;
import ua.epam.training.kukuruza.model.entity.Subject;
import ua.epam.training.kukuruza.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class SubmitRequest implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer universityId = Integer.valueOf(req.getParameter("universityId"));
        Integer specialtyId = Integer.valueOf(req.getParameter("specialtyId"));
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
            return "/WEB-INF/jsp/user/specialtySelection.jsp";
        } else {
            Specialty chosenSpecialty = service.submitRequest(user.getId(), rating, universityId, specialtyId);
            req.setAttribute("chosenSpecialty", chosenSpecialty);
        }

        return "/WEB-INF/jsp/user/universitySelection.jsp";
    }
}
