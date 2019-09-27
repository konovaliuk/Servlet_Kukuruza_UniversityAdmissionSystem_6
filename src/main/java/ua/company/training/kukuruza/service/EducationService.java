package ua.company.training.kukuruza.service;

import ua.company.training.kukuruza.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.entity.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class EducationService {
    private AbstractDaoFactory factory;

    public EducationService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public Optional<Specialty> getChosenSpecialty(Long userId) {
        Optional<Request> userRequest = factory.getDaoRequest().findByUserId(userId);
        if (userRequest.isPresent()) {
            Long educationOptionId = userRequest.get().getEducationOptionId();
            Optional<EducationOption> educationOption = factory.getDaoEducationOption().find(educationOptionId);
            return factory.getDaoSpecialty().find(educationOption.orElseThrow(RuntimeException::new).getSpecialtyId());
        } else {
            return Optional.empty();
        }
    }

    public List<University> getUniversities() {
        return factory.getDaoUniversity().findAll();
    }

    public List<Specialty> getSpecialties(Integer universityId) {
        Set<Integer> specialtiesId = factory.getDaoEducationOption().findSpecialtiesIdByUniversityId(universityId);
        return factory.getDaoSpecialty().findByIdSet(specialtiesId);
    }

    public Integer getRatingByRequiredSubjects(Long userId, Integer specialtyId) {
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
        return rating;
    }

    public Specialty getSpecialty(Integer specialtyId) {
        return factory.getDaoSpecialty().find(specialtyId).orElseThrow(RuntimeException::new);
    }

    public List<Subject> getRequiredSubjects(Integer specialtyId) {
        Set<Integer> subjectsId = factory.getDaoSpecialtySubject().findSubjectsIdBySpecialtyId(specialtyId);
        return factory.getDaoSubject().findByIdSet(subjectsId);
    }

    public Specialty submitRequest(Long userId, Integer rating, Integer universityId, Integer specialtyId) {
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
        factory.getDaoRequest().deleteByUserId(userId);
    }
}
