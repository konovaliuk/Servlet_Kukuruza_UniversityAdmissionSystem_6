package ua.company.training.kukuruza.command.impl;

import ua.company.training.kukuruza.service.EducationService;
import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.entity.Specialty;
import ua.company.training.kukuruza.entity.University;
import ua.company.training.kukuruza.entity.User;
import ua.company.training.kukuruza.command.ICommand;
import ua.company.training.kukuruza.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class UniversitySelection implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute(AttributeNames.USER);

        EducationService service = ServiceFactory.getInstance().getEducationService();
        Optional<Specialty> chosenSpecialty = service.getChosenSpecialty(user.getId());
        if (chosenSpecialty.isPresent()) {
            req.setAttribute(AttributeNames.CHOSEN_SPECIALTY, chosenSpecialty.get());
        } else {
            List<University> universities = service.getUniversities();
            req.setAttribute(AttributeNames.UNIVERSITIES, universities);
        }

        return Path.UNIVERSITY_SELECTION_PAGE;
    }
}
