package ua.company.training.kukuruza.controller.service;

import ua.company.training.kukuruza.model.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.model.entity.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SummaryRatingService {
    private AbstractDaoFactory factory;

    public SummaryRatingService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public Optional<Request> getUserRequest(User user) {
        return factory.getDaoRequest().getByUserId(user.getId());
    }

    public Map<Long, Integer> getUserIdToRating(Request userRequest) {
        List<Request> requests = factory.getDaoRequest().getByEducationOptionId(userRequest.getEducationOptionId());
        return requests.stream()
                .sorted(Comparator.comparingInt(Request::getRating).reversed())
                .collect(Collectors.toMap(Request::getUserId, Request::getRating, (a, b) -> a, LinkedHashMap::new));
    }

    public Map<Long, User> getUserIdToUserByIdSet(Set<Long> usersId) {
        List<User> users = factory.getDaoUser().getByIdSet(usersId);
        return users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
    }

    public String getUniversityName(Request userRequest) {
        Optional<EducationOption> educationOption =
                factory.getDaoEducationOption().get(userRequest.getEducationOptionId());
        Integer universityId = educationOption.orElseThrow(RuntimeException::new).getUniversityId();
        Optional<University> university = factory.getDaoUniversity().get(universityId);
        return university.orElseThrow(RuntimeException::new).getName();
    }

    public String getSpecialtyName(Request userRequest) {
        Optional<EducationOption> educationOption =
                factory.getDaoEducationOption().get(userRequest.getEducationOptionId());
        Integer specialtyId = educationOption.orElseThrow(RuntimeException::new).getSpecialtyId();
        Optional<Specialty> specialty = factory.getDaoSpecialty().get(specialtyId);
        return specialty.orElseThrow(RuntimeException::new).getName();
    }

    public Integer getStudentLimit(Request request) {
        Optional<EducationOption> educationOption = factory.getDaoEducationOption().get(request.getEducationOptionId());
        return educationOption.orElseThrow(RuntimeException::new).getStudentLimit();
    }
}
