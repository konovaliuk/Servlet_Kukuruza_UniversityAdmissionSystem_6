package ua.company.training.kukuruza.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.company.training.kukuruza.dao.*;
import ua.company.training.kukuruza.entity.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EducationServiceTest {
    @InjectMocks
    private EducationService service;
    @Mock
    private AbstractDaoFactory factory;

    @Test
    void getChosenSpecialtyWithExistingChosenSpecialtyTest() {
        Long userId = 42L;
        Long educationOptionId = 24L;
        Integer specialtyId = 7;
        Request request = new Request.Builder()
                .setUserId(userId)
                .setEducationOptionId(educationOptionId)
                .build();
        EducationOption educationOption = new EducationOption.Builder()
                .setId(educationOptionId)
                .setSpecialtyId(specialtyId)
                .build();
        Specialty specialty = new Specialty.Builder()
                .setId(specialtyId)
                .build();
        IDaoRequest daoRequest = mock(IDaoRequest.class);
        IDaoEducationOption daoEducationOption = mock(IDaoEducationOption.class);
        IDaoSpecialty daoSpecialty = mock(IDaoSpecialty.class);
        when(factory.getDaoRequest()).thenReturn(daoRequest);
        when(factory.getDaoEducationOption()).thenReturn(daoEducationOption);
        when(factory.getDaoSpecialty()).thenReturn(daoSpecialty);
        when(daoRequest.findByUserId(userId)).thenReturn(Optional.of(request));
        when(daoEducationOption.find(educationOptionId)).thenReturn(Optional.of(educationOption));
        when(daoSpecialty.find(specialtyId)).thenReturn(Optional.of(specialty));

        Optional<Specialty> actual = service.getChosenSpecialty(userId);

        assertEquals(specialtyId, actual.orElseThrow(RuntimeException::new).getId());
    }

    @Test
    void getChosenSpecialtyWithNotExistingChosenSpecialtyTest() {
        Long userId = 42L;
        IDaoRequest daoRequest = mock(IDaoRequest.class);
        when(factory.getDaoRequest()).thenReturn(daoRequest);
        when(daoRequest.findByUserId(userId)).thenReturn(Optional.empty());

        Optional<Specialty> actual = service.getChosenSpecialty(userId);

        assertTrue(!actual.isPresent());
        verify(factory, never()).getDaoEducationOption();
        verify(factory, never()).getDaoSpecialty();
    }

    @Test
    void getUniversitiesTest() {
        IDaoUniversity daoUniversity = mock(IDaoUniversity.class);
        when(factory.getDaoUniversity()).thenReturn(daoUniversity);

        service.getUniversities();

        verify(daoUniversity, times(1)).findAll();
    }

    @Test
    void getSpecialtiesWithCorrectUniversityIdTest() {
        Integer universityId = 42;
        Integer specialtyId = 7;
        Set<Integer> specialtiesId = new HashSet<>();
        specialtiesId.add(specialtyId);
        Specialty specialty = new Specialty.Builder()
                .setId(specialtyId)
                .build();
        List<Specialty> specialties = new ArrayList<>();
        specialties.add(specialty);
        IDaoEducationOption daoEducationOption = mock(IDaoEducationOption.class);
        IDaoSpecialty daoSpecialty = mock(IDaoSpecialty.class);
        when(factory.getDaoEducationOption()).thenReturn(daoEducationOption);
        when(factory.getDaoSpecialty()).thenReturn(daoSpecialty);
        when(daoEducationOption.findSpecialtiesIdByUniversityId(universityId)).thenReturn(specialtiesId);
        when(daoSpecialty.findByIdSet(specialtiesId)).thenReturn(specialties);

        List<Specialty> actual = service.getSpecialties(universityId);

        assertEquals(1, actual.size());
        assertEquals(specialtyId, actual.get(0).getId());
    }

    @Test
    void getSpecialtiesWithWrongUniversityIdTest() {
        Integer universityId = 42;
        Set<Integer> specialtiesId = Collections.emptySet();
        IDaoEducationOption daoEducationOption = mock(IDaoEducationOption.class);
        IDaoSpecialty daoSpecialty = mock(IDaoSpecialty.class);
        when(factory.getDaoEducationOption()).thenReturn(daoEducationOption);
        when(factory.getDaoSpecialty()).thenReturn(daoSpecialty);
        when(daoEducationOption.findSpecialtiesIdByUniversityId(universityId)).thenReturn(specialtiesId);
        when(daoSpecialty.findByIdSet(specialtiesId)).thenReturn(Collections.emptyList());

        List<Specialty> specialties = service.getSpecialties(universityId);

        assertTrue(specialties.isEmpty());
    }

    @Test
    void getRatingByRequiredSubjectsTest() {
        Long userId = 42L;
        Integer specialtyId = 7;
        Integer subjectId1 = 1;
        Integer subjectId2 = 2;
        Integer result1 = 150;
        Integer result2 = 175;
        Set<Integer> subjectsId = new HashSet<>();
        subjectsId.add(subjectId1);
        subjectsId.add(subjectId2);
        Grade grade1 = new Grade.Builder()
                .setSubjectId(subjectId1)
                .setResult(result1)
                .build();
        Grade grade2 = new Grade.Builder()
                .setSubjectId(subjectId2)
                .setResult(result2)
                .build();
        IDaoSpecialtySubject daoSpecialtySubject = mock(IDaoSpecialtySubject.class);
        IDaoGrade daoGrade = mock(IDaoGrade.class);
        when(factory.getDaoSpecialtySubject()).thenReturn(daoSpecialtySubject);
        when(factory.getDaoGrade()).thenReturn(daoGrade);
        when(daoSpecialtySubject.findSubjectsIdBySpecialtyId(specialtyId)).thenReturn(subjectsId);
        when(daoGrade.findByUserIdAndSubjectId(userId, subjectId1)).thenReturn(Optional.of(grade1));
        when(daoGrade.findByUserIdAndSubjectId(userId, subjectId2)).thenReturn(Optional.of(grade2));

        Integer actual = service.getRatingByRequiredSubjects(userId, specialtyId);

        assertEquals(result1 + result2, actual);
    }

    @Test
    void getRatingByRequiredSubjectsWithNotAllRequiredGradesTest() {
        Long userId = 42L;
        Integer specialtyId = 7;
        Integer subjectId1 = 1;
        Integer subjectId2 = 2;
        Integer result1 = 150;
        Set<Integer> subjectsId = new HashSet<>();
        subjectsId.add(subjectId1);
        subjectsId.add(subjectId2);
        Grade grade1 = new Grade.Builder()
                .setSubjectId(subjectId1)
                .setResult(result1)
                .build();
        IDaoSpecialtySubject daoSpecialtySubject = mock(IDaoSpecialtySubject.class);
        IDaoGrade daoGrade = mock(IDaoGrade.class);
        when(factory.getDaoSpecialtySubject()).thenReturn(daoSpecialtySubject);
        when(factory.getDaoGrade()).thenReturn(daoGrade);
        when(daoSpecialtySubject.findSubjectsIdBySpecialtyId(specialtyId)).thenReturn(subjectsId);
        when(daoGrade.findByUserIdAndSubjectId(userId, subjectId1)).thenReturn(Optional.of(grade1));
        when(daoGrade.findByUserIdAndSubjectId(userId, subjectId2)).thenReturn(Optional.empty());

        ServiceException e = assertThrows(ServiceException.class,
                () -> service.getRatingByRequiredSubjects(userId, specialtyId));
        assertEquals("No grade", e.getMessage());
    }

    @Test
    void getRatingByRequiredSubjectsWithWrongSpecialtyIdTest() {
        Long userId = 42L;
        Integer wrongSpecialtyId = 7;
        IDaoSpecialtySubject daoSpecialtySubject = mock(IDaoSpecialtySubject.class);
        when(factory.getDaoSpecialtySubject()).thenReturn(daoSpecialtySubject);
        when(daoSpecialtySubject.findSubjectsIdBySpecialtyId(wrongSpecialtyId)).thenReturn(Collections.emptySet());

        Integer actual = service.getRatingByRequiredSubjects(userId, wrongSpecialtyId);

        assertEquals(0, actual);
    }

    @Test
    void getSpecialtyWithCorrectSpecialtyIdTest() {
        Integer specialtyId = 42;
        Specialty expected = new Specialty.Builder()
                .setId(specialtyId)
                .build();
        IDaoSpecialty daoSpecialty = mock(IDaoSpecialty.class);
        when(factory.getDaoSpecialty()).thenReturn(daoSpecialty);
        when(daoSpecialty.find(specialtyId)).thenReturn(Optional.of(expected));

        Specialty actual = service.getSpecialty(specialtyId);

        assertEquals(specialtyId, actual.getId());
    }

    @Test
    void getSpecialtyWithWrongSpecialtyIdTest() {
        Integer wrongSpecialtyId = 42;
        IDaoSpecialty daoSpecialty = mock(IDaoSpecialty.class);
        when(factory.getDaoSpecialty()).thenReturn(daoSpecialty);
        when(daoSpecialty.find(wrongSpecialtyId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getSpecialty(wrongSpecialtyId));
    }

    @Test
    void getRequiredSubjectsWithCorrectSpecialtyIdTest() {
        Integer specialtyId = 42;
        Integer subjectId = 7;
        Set<Integer> subjectsId = new HashSet<>();
        subjectsId.add(subjectId);
        Subject subject = new Subject.Builder()
                .setId(subjectId)
                .build();
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        IDaoSpecialtySubject daoSpecialtySubject = mock(IDaoSpecialtySubject.class);
        IDaoSubject daoSubject = mock(IDaoSubject.class);
        when(factory.getDaoSpecialtySubject()).thenReturn(daoSpecialtySubject);
        when(factory.getDaoSubject()).thenReturn(daoSubject);
        when(daoSpecialtySubject.findSubjectsIdBySpecialtyId(specialtyId)).thenReturn(subjectsId);
        when(daoSubject.findByIdSet(subjectsId)).thenReturn(subjects);

        List<Subject> actual = service.getRequiredSubjects(specialtyId);

        assertEquals(1, actual.size());
        assertEquals(subjectId, actual.get(0).getId());
    }

    @Test
    void getRequiredSubjectsWithWrongSpecialtyIdTest() {
        Integer wrongSpecialtyId = 42;
        Set<Integer> subjectsId = Collections.emptySet();
        IDaoSpecialtySubject daoSpecialtySubject = mock(IDaoSpecialtySubject.class);
        IDaoSubject daoSubject = mock(IDaoSubject.class);
        when(factory.getDaoSpecialtySubject()).thenReturn(daoSpecialtySubject);
        when(factory.getDaoSubject()).thenReturn(daoSubject);
        when(daoSpecialtySubject.findSubjectsIdBySpecialtyId(wrongSpecialtyId)).thenReturn(subjectsId);
        when(daoSubject.findByIdSet(subjectsId)).thenReturn(Collections.emptyList());

        List<Subject> actual = service.getRequiredSubjects(wrongSpecialtyId);

        assertTrue(actual.isEmpty());
    }

    @Test
    void submitRequestTest() {
        Long userId = 42L;
        Integer rating = 420;
        Integer universityId = 7;
        Integer specialtyId = 13;
        Long educationOptionId = 4200L;
        EducationOption educationOption = new EducationOption.Builder()
                .setId(educationOptionId)
                .setSpecialtyId(specialtyId)
                .setUniversityId(universityId)
                .build();
        Request request = new Request.Builder()
                .setUserId(userId)
                .setRating(rating)
                .setEducationOptionId(educationOptionId)
                .build();
        Specialty specialty = new Specialty.Builder()
                .setId(specialtyId)
                .build();
        IDaoEducationOption daoEducationOption = mock(IDaoEducationOption.class);
        IDaoRequest daoRequest = mock(IDaoRequest.class);
        IDaoSpecialty daoSpecialty = mock(IDaoSpecialty.class);
        when(factory.getDaoEducationOption()).thenReturn(daoEducationOption);
        when(factory.getDaoRequest()).thenReturn(daoRequest);
        when(factory.getDaoSpecialty()).thenReturn(daoSpecialty);
        when(daoEducationOption.findByUniversityIdAndSpecialtyId(universityId, specialtyId))
                .thenReturn(Optional.of(educationOption));
        when(daoSpecialty.find(specialtyId)).thenReturn(Optional.of(specialty));

        Specialty actual = service.submitRequest(userId, rating, universityId, specialtyId);

        verify(daoRequest, times(1)).save(request);
        assertEquals(specialtyId, actual.getId());
    }

    @Test
    void submitRequestWithWrongUniversityIdTest() {
        Long userId = 42L;
        Integer rating = 420;
        Integer wrongUniversityId = 7;
        Integer specialtyId = 13;
        IDaoEducationOption daoEducationOption = mock(IDaoEducationOption.class);
        when(factory.getDaoEducationOption()).thenReturn(daoEducationOption);
        when(daoEducationOption.findByUniversityIdAndSpecialtyId(wrongUniversityId, specialtyId))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.submitRequest(userId, rating, wrongUniversityId, specialtyId));
    }

    @Test
    void dropRequestTest() {
        Long userId = 42L;
        IDaoRequest daoRequest = mock(IDaoRequest.class);
        when(factory.getDaoRequest()).thenReturn(daoRequest);

        service.dropRequest(userId);

        verify(daoRequest, times(1)).deleteByUserId(userId);
    }
}
