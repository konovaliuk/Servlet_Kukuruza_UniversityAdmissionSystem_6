package ua.company.training.kukuruza.controller.service;

import ua.company.training.kukuruza.model.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.model.entity.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class EducationService {
    private AbstractDaoFactory factory;

    public EducationService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public Optional<Specialty> getChosenSpecialty(Long userId) {
        Optional<Request> userRequest = factory.getDaoRequest().getByUserId(userId);
        if (userRequest.isPresent()) {
            Long educationOptionId = userRequest.get().getEducationOptionId();
            Optional<EducationOption> educationOption = factory.getDaoEducationOption().get(educationOptionId);
            return factory.getDaoSpecialty().get(educationOption.orElseThrow(RuntimeException::new).getSpecialtyId());
        } else {
            return Optional.empty();
        }
    }

    public List<University> getUniversities() {
        return factory.getDaoUniversity().getAll();
    }

    public List<Specialty> getSpecialties(Integer universityId) {
        Set<Integer> specialtiesId = factory.getDaoEducationOption().getSpecialtiesIdByUniversityId(universityId);
        return factory.getDaoSpecialty().getByIdSet(specialtiesId);
    }

    public Integer getRatingByRequiredSubjects(Long userId, Integer specialtyId) {
        Set<Integer> subjectsId = factory.getDaoSpecialtySubject().getSubjectsIdBySpecialtyId(specialtyId);
        int rating = 0;
        for (Integer subjectId : subjectsId) {
            Optional<Grade> grade = factory.getDaoGrade().getByUserIdAndSubjectId(userId, subjectId);
            if (grade.isPresent()) {
                rating += grade.get().getResult();
            } else {
                return null;
            }
        }
        return rating;
    }

    public Specialty getSpecialty(Integer specialtyId) {
        return factory.getDaoSpecialty().get(specialtyId).orElseThrow(RuntimeException::new);
    }

    public List<Subject> getRequiredSubjects(Integer specialtyId) {
        Set<Integer> subjectsId = factory.getDaoSpecialtySubject().getSubjectsIdBySpecialtyId(specialtyId);
        return factory.getDaoSubject().getByIdSet(subjectsId);
    }

    public Specialty submitRequest(Long userId, Integer rating, Integer universityId, Integer specialtyId) {
        Optional<EducationOption> educationOption =
                factory.getDaoEducationOption().getByUniversityIdAndSpecialtyId(universityId, specialtyId);
        Request request = new Request.Builder()
                .setUserId(userId)
                .setRating(rating)
                .setEducationOptionId(educationOption.orElseThrow(RuntimeException::new).getId())
                .build();
        factory.getDaoRequest().save(request);
        return factory.getDaoSpecialty().get(specialtyId).orElseThrow(RuntimeException::new);
    }

    public void dropRequest(Long userId) {
        factory.getDaoRequest().deleteByUserId(userId);
    }
}
