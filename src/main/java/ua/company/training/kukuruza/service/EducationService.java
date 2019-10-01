package ua.company.training.kukuruza.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.entity.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class EducationService {
    private static final Logger LOGGER = LogManager.getLogger(EducationService.class);
    private AbstractDaoFactory factory;

    public EducationService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public Optional<Specialty> getChosenSpecialty(Long userId) {
        LOGGER.info("Try to get user chosen specialty");
        Optional<Request> userRequest = factory.getDaoRequest().findByUserId(userId);
        if (userRequest.isPresent()) {
            Long educationOptionId = userRequest.get().getEducationOptionId();
            Optional<EducationOption> educationOption = factory.getDaoEducationOption().find(educationOptionId);
            LOGGER.info("User request exists. Try to get a specialty");
            return factory.getDaoSpecialty().find(educationOption.orElseThrow(RuntimeException::new).getSpecialtyId());
        } else {
            LOGGER.info("There is no user chosen specialty");
            return Optional.empty();
        }
    }

    public List<University> getUniversities(Integer skip, Integer limit) {
        LOGGER.info("Try to find a list of universities for a page");
        return factory.getDaoUniversity().findAll(skip, limit);
    }

    public Integer getRatingByRequiredSubjects(Long userId, Integer specialtyId) {
        LOGGER.info("Try to get rating by required subjects");
        Set<Integer> subjectsId = factory.getDaoSpecialtySubject().findSubjectsIdBySpecialtyId(specialtyId);
        int rating = 0;
        for (Integer subjectId : subjectsId) {
            Optional<Grade> grade = factory.getDaoGrade().findByUserIdAndSubjectId(userId, subjectId);
            if (grade.isPresent()) {
                rating += grade.get().getResult();
            } else {
                throw new ServiceException("No grade");
            }
        }
        LOGGER.info("Rating by required subjects was successfully calculated");
        return rating;
    }

    public Specialty getSpecialty(Integer specialtyId) {
        LOGGER.info("Try to find a specialty by id");
        return factory.getDaoSpecialty().find(specialtyId).orElseThrow(RuntimeException::new);
    }

    public List<Specialty> getSpecialties(Integer universityId, Integer skip, Integer limit) {
        LOGGER.info("Try to find a list of specialties for a page");
        Set<Integer> specialtiesId =
                factory.getDaoEducationOption().findSpecialtiesIdByUniversityId(universityId, skip, limit);
        return factory.getDaoSpecialty().findByIdSet(specialtiesId);
    }

    public List<Subject> getRequiredSubjects(Integer specialtyId) {
        LOGGER.info("Try to find a list of required subjects");
        Set<Integer> subjectsId = factory.getDaoSpecialtySubject().findSubjectsIdBySpecialtyId(specialtyId);
        return factory.getDaoSubject().findByIdSet(subjectsId);
    }

    public Specialty submitRequest(Long userId, Integer rating, Integer universityId, Integer specialtyId) {
        LOGGER.info("Try to submit user request");
        Optional<EducationOption> educationOption =
                factory.getDaoEducationOption().findByUniversityIdAndSpecialtyId(universityId, specialtyId);
        Request request = new Request.Builder()
                .setUserId(userId)
                .setRating(rating)
                .setEducationOptionId(educationOption.orElseThrow(RuntimeException::new).getId())
                .build();
        factory.getDaoRequest().save(request);
        return factory.getDaoSpecialty().find(specialtyId).orElseThrow(RuntimeException::new);
    }

    public void dropRequest(Long userId) {
        LOGGER.info("Try to drop user request");
        factory.getDaoRequest().deleteByUserId(userId);
    }

    public Long getSpecialtiesRowsCount(Integer universityId) {
        LOGGER.info("Try to get specialties rows count");
        return factory.getDaoEducationOption().getSpecialtiesRowsCount(universityId);
    }

    public Long getUniversitiesRowsCount() {
        LOGGER.info("Try to get universities rows count");
        return factory.getDaoUniversity().getRowsCount();
    }
}
