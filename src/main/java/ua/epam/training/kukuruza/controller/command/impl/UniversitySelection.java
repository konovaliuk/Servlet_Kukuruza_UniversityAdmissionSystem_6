package ua.epam.training.kukuruza.controller.command.impl;

import ua.epam.training.kukuruza.controller.command.ICommand;
import ua.epam.training.kukuruza.controller.service.EducationService;
import ua.epam.training.kukuruza.controller.service.ServiceFactory;
import ua.epam.training.kukuruza.model.entity.Specialty;
import ua.epam.training.kukuruza.model.entity.University;
import ua.epam.training.kukuruza.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class UniversitySelection implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");

        EducationService service = ServiceFactory.getInstance().getEducationService();
        Optional<Specialty> chosenSpecialty = service.getChosenSpecialty(user.getId());
        if (chosenSpecialty.isPresent()) {
            req.setAttribute("chosenSpecialty", chosenSpecialty.get());
        } else {
            List<University> universities = service.getUniversities();
            req.setAttribute("universities", universities);
        }

        return "/WEB-INF/jsp/user/universitySelection.jsp";
    }
}
