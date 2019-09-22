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

    public Optional<Request> getUserRequest(Long userId) {
        return factory.getDaoRequest().findByUserId(userId);
    }

    public Map<Long, Integer> getUserIdToRating(Long educationOptionId) {
        List<Request> requests = factory.getDaoRequest().findByEducationOptionId(educationOptionId);
        return requests.stream()
                .sorted(Comparator.comparingInt(Request::getRating).reversed())
                .collect(Collectors.toMap(Request::getUserId, Request::getRating, (a, b) -> a, LinkedHashMap::new));
    }

    public Map<Long, User> getUserIdToUserByIdSet(Set<Long> usersId) {
        List<User> users = factory.getDaoUser().findByIdSet(usersId);
        return users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
    }

    public String getUniversityName(Long educationOptionId) {
        Optional<EducationOption> educationOption = factory.getDaoEducationOption().find(educationOptionId);
        Integer universityId = educationOption.orElseThrow(RuntimeException::new).getUniversityId();
        Optional<University> university = factory.getDaoUniversity().find(universityId);
        return university.orElseThrow(RuntimeException::new).getName();
    }

    public String getSpecialtyName(Long educationOptionId) {
        Optional<EducationOption> educationOption = factory.getDaoEducationOption().find(educationOptionId);
        Integer specialtyId = educationOption.orElseThrow(RuntimeException::new).getSpecialtyId();
        Optional<Specialty> specialty = factory.getDaoSpecialty().find(specialtyId);
        return specialty.orElseThrow(RuntimeException::new).getName();
    }

    public Integer getStudentLimit(Long educationOptionId) {
        Optional<EducationOption> educationOption = factory.getDaoEducationOption().find(educationOptionId);
        return educationOption.orElseThrow(RuntimeException::new).getStudentLimit();
    }
}
