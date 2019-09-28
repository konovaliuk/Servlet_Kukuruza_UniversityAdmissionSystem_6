package ua.company.training.kukuruza.command.impl;

import ua.company.training.kukuruza.util.PaginationManager;
import ua.company.training.kukuruza.service.EducationService;
import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.util.RequestParameters;
import ua.company.training.kukuruza.entity.Specialty;
import ua.company.training.kukuruza.command.ICommand;
import ua.company.training.kukuruza.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SpecialtySelection implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer universityId = Integer.valueOf(req.getParameter(RequestParameters.UNIVERSITY_ID));
        EducationService service = ServiceFactory.getInstance().getEducationService();
        Long rowsCount = service.getSpecialtiesRowsCount(universityId);
        Integer skip = PaginationManager.manage(req, rowsCount);
        List<Specialty> specialties = service.getSpecialties(universityId, skip, PaginationManager.RECORDS_PER_PAGE);
        req.setAttribute(AttributeNames.SPECIALTIES, specialties);
        return Path.SPECIALTY_SELECTION_PAGE;
    }
}
