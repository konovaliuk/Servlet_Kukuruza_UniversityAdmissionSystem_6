package ua.company.training.kukuruza.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.company.training.kukuruza.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.dao.IDaoGrade;
import ua.company.training.kukuruza.dao.IDaoSubject;
import ua.company.training.kukuruza.entity.Grade;
import ua.company.training.kukuruza.entity.Subject;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResultServiceTest {
    @InjectMocks
    private ResultService service;
    @Mock
    private AbstractDaoFactory factory;
    @Mock
    private IDaoGrade daoGrade;
    @Mock
    private IDaoSubject daoSubject;

    @Test
    void getUserGradesTest() {
        Long userId = 42L;
        Integer subjectId1 = 1;
        Integer subjectId2 = 2;
        Integer result1 = 125;
        Integer result2 = 175;
        String subjectName1 = "subjectName1";
        String subjectName2 = "subjectName2";
        Grade grade1 = new Grade.Builder()
                .setSubjectId(subjectId1)
                .setResult(result1)
                .build();
        Grade grade2 = new Grade.Builder()
                .setSubjectId(subjectId2)
                .setResult(result2)
                .build();
        List<Grade> grades = new ArrayList<>();
        grades.add(grade1);
        grades.add(grade2);
        Subject subject1 = new Subject.Builder()
                .setId(subjectId1)
                .setName(subjectName1)
                .build();
        Subject subject2 = new Subject.Builder()
                .setId(subjectId2)
                .setName(subjectName2)
                .build();
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);
        when(factory.getDaoGrade()).thenReturn(daoGrade);
        when(factory.getDaoSubject()).thenReturn(daoSubject);
        when(daoGrade.findByUserId(userId)).thenReturn(grades);
        when(daoSubject.findAll()).thenReturn(subjects);
        Map<String, Integer> expected = new HashMap<>();
        expected.put(subjectName1, result1);
        expected.put(subjectName2, result2);

        Map<String, Integer> actual = service.getUserGrades(userId);

        assertEquals(expected, actual);
    }

    @Test
    void getUserGradesWithNoGradesTest() {
        Long userId = 42L;
        when(factory.getDaoGrade()).thenReturn(daoGrade);
        when(daoGrade.findByUserId(userId)).thenReturn(Collections.emptyList());

        Map<String, Integer> actual = service.getUserGrades(userId);

        assertTrue(actual.isEmpty());
    }
}
