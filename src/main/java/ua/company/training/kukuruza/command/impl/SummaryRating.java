package ua.company.training.kukuruza.command.impl;

import ua.company.training.kukuruza.service.SummaryRatingService;
import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.entity.Request;
import ua.company.training.kukuruza.entity.User;
import ua.company.training.kukuruza.command.ICommand;
import ua.company.training.kukuruza.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public class SummaryRating implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute(AttributeNames.USER);

        SummaryRatingService service = ServiceFactory.getSummaryRatingService();
        Optional<Request> userRequest = service.getUserRequest(user.getId());
        if (userRequest.isPresent()) {
            Map<Long, Integer> userIdToRating = service.
                    getUserIdToRatingOrderByRating(userRequest.get().getEducationOptionId());
            Map<Long, User> userIdToUser = service.getUserIdToUserByIdSet(userIdToRating.keySet());
            String universityName = service.getUniversityName(userRequest.get().getEducationOptionId());
            String specialtyName = service.getSpecialtyName(userRequest.get().getEducationOptionId());
            Integer studentLimit = service.getStudentLimit(userRequest.get().getEducationOptionId());

            req.setAttribute(AttributeNames.USER_ID_TO_RATING, userIdToRating);
            req.setAttribute(AttributeNames.USER_ID_TO_USER, userIdToUser);
            req.setAttribute(AttributeNames.UNIVERSITY_NAME, universityName);
            req.setAttribute(AttributeNames.SPECIALTY_NAME, specialtyName);
            req.setAttribute(AttributeNames.STUDENT_LIMIT, studentLimit);
        }

        return Path.SUMMARY_RATING_PAGE;
    }
}
