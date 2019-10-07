package ua.company.training.kukuruza.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.persistence.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.persistence.entity.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SummaryRatingService {
    private static final Logger LOGGER = LogManager.getLogger(SummaryRatingService.class);
    private AbstractDaoFactory factory;

    SummaryRatingService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public Optional<Request> getUserRequest(Long userId) {
        LOGGER.info("Try to get user request by userId");
        return factory.getDaoRequest().findByUserId(userId);
    }

    public Map<Long, Integer> getUserIdToRatingOrderByRating(Long educationOptionId) {
        LOGGER.info("Try to get user grades");
        List<Request> requests = factory.getDaoRequest().findByEducationOptionId(educationOptionId);
        return requests.stream()
                .sorted(Comparator.comparingInt(Request::getRating).reversed())
                .collect(Collectors.toMap(Request::getUserId, Request::getRating, (a, b) -> a, LinkedHashMap::new));
    }

    public Map<Long, User> getUserIdToUserByIdSet(Set<Long> usersId) {
        LOGGER.info("Try to get a map of userId to user");
        List<User> users = factory.getDaoUser().findByIdSet(usersId);
        return users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
    }

    public String getUniversityName(Long educationOptionId) {
        LOGGER.info("Try to get university name by educationOptionId");
        Optional<EducationOption> educationOption = factory.getDaoEducationOption().find(educationOptionId);
        Integer universityId = educationOption.orElseThrow(RuntimeException::new).getUniversityId();
        Optional<University> university = factory.getDaoUniversity().find(universityId);
        return university.orElseThrow(RuntimeException::new).getName();
    }

    public String getSpecialtyName(Long educationOptionId) {
        LOGGER.info("Try to get specialty name by educationOptionId");
        Optional<EducationOption> educationOption = factory.getDaoEducationOption().find(educationOptionId);
        Integer specialtyId = educationOption.orElseThrow(RuntimeException::new).getSpecialtyId();
        Optional<Specialty> specialty = factory.getDaoSpecialty().find(specialtyId);
        return specialty.orElseThrow(RuntimeException::new).getName();
    }

    public Integer getStudentLimit(Long educationOptionId) {
        LOGGER.info("Try to get student limit of an educationOption");
        Optional<EducationOption> educationOption = factory.getDaoEducationOption().find(educationOptionId);
        return educationOption.orElseThrow(RuntimeException::new).getStudentLimit();
    }
}
