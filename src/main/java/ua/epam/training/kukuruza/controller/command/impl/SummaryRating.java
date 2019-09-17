package ua.epam.training.kukuruza.controller.command.impl;

import ua.epam.training.kukuruza.controller.command.ICommand;
import ua.epam.training.kukuruza.controller.service.ServiceFactory;
import ua.epam.training.kukuruza.controller.service.SummaryRatingService;
import ua.epam.training.kukuruza.model.entity.Request;
import ua.epam.training.kukuruza.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public class SummaryRating implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");

        SummaryRatingService service = ServiceFactory.getInstance().getSummaryRatingService();
        Optional<Request> userRequest = service.getUserRequest(user);
        if (userRequest.isPresent()) {
            Map<Long, Integer> userIdToRating = service.getUserIdToRating(userRequest.get());
            Map<Long, User> userIdToUser = service.getUserIdToUserByIdSet(userIdToRating.keySet());
            String universityName = service.getUniversityName(userRequest.get());
            String specialtyName = service.getSpecialtyName(userRequest.get());
            Integer studentLimit = service.getStudentLimit(userRequest.get());

            req.setAttribute("userIdToRating", userIdToRating);
            req.setAttribute("userIdToUser", userIdToUser);
            req.setAttribute("universityName", universityName);
            req.setAttribute("specialtyName", specialtyName);
            req.setAttribute("studentLimit", studentLimit);
        }

        return "/WEB-INF/jsp/user/summaryRating.jsp";
    }
}
