package ua.company.training.kukuruza.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.company.training.kukuruza.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.dao.IDaoExam;
import ua.company.training.kukuruza.dao.IDaoSubject;
import ua.company.training.kukuruza.dao.IDaoUserExam;
import ua.company.training.kukuruza.entity.Exam;
import ua.company.training.kukuruza.entity.Subject;
import ua.company.training.kukuruza.entity.UserExam;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamServiceTest {
    @InjectMocks
    private ExamService service;
    @Mock
    private AbstractDaoFactory factory;

    @Test
    void getUserExamsTest() {
        Long userId = 42L;
        Integer userExamId1 = 1;
        Integer userExamId2 = 2;
        Set<Integer> userExamsId = new HashSet<>();
        userExamsId.add(userExamId1);
        userExamsId.add(userExamId2);
        Exam exam1 = new Exam.Builder()
                .setId(userExamId1)
                .setAddress("address1")
                .build();
        Exam exam2 = new Exam.Builder()
                .setId(userExamId2)
                .setAddress("address2")
                .build();
        List<Exam> expected = new ArrayList<>();
        expected.add(exam1);
        expected.add(exam2);
        IDaoUserExam daoUserExam = mock(IDaoUserExam.class);
        IDaoExam daoExam = mock(IDaoExam.class);
        when(factory.getDaoUserExam()).thenReturn(daoUserExam);
        when(factory.getDaoExam()).thenReturn(daoExam);
        when(daoUserExam.findExamsIdByUserId(userId)).thenReturn(userExamsId);
        when(daoExam.findByIdSet(userExamsId)).thenReturn(expected);

        List<Exam> actual = service.getUserExams(userId);

        assertEquals(expected, actual);
    }

    @Test
    void getAvailableExamsTest() {
        Integer userExamId = 42;
        Integer availableExamId = 7;
        Set<Integer> userExamsId = new HashSet<>();
        userExamsId.add(userExamId);
        Exam userExam = new Exam.Builder()
                .setId(userExamId)
                .build();
        List<Exam> userExams = new ArrayList<>();
        userExams.add(userExam);
        Exam availableExam = new Exam.Builder()
                .setId(availableExamId)
                .build();
        List<Exam> expected = new ArrayList<>();
        expected.add(availableExam);
        IDaoExam daoExam = mock(IDaoExam.class);
        when(factory.getDaoExam()).thenReturn(daoExam);
        when(daoExam.findNotInIdSet(userExamsId)).thenReturn(expected);

        List<Exam> actual = service.getAvailableExams(userExams);

        assertEquals(expected, actual);
    }

    @Test
    void getSubjectIdToSubjectNameTest() {
        Integer subjectId1 = 1;
        Integer subjectId2 = 2;
        String subjectName1 = "subjectName1";
        String subjectName2 = "subjectName2";
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
        Map<Integer, String> expected = new HashMap<>();
        expected.put(subjectId1, subjectName1);
        expected.put(subjectId2, subjectName2);
        IDaoSubject daoSubject = mock(IDaoSubject.class);
        when(factory.getDaoSubject()).thenReturn(daoSubject);
        when(daoSubject.findAll()).thenReturn(subjects);

        Map<Integer, String> actual = service.getSubjectIdToSubjectName();

        assertEquals(expected, actual);
    }

    @Test
    void registerUserToExamsTest() {
        Long userId = 42L;
        String examId1 = "1";
        String examId2 = "2";
        String[] examsId = new String[]{examId1, examId2};
        UserExam userExam1 = new UserExam.Builder()
                .setExamId(1)
                .setUserId(userId)
                .build();
        UserExam userExam2 = new UserExam.Builder()
                .setExamId(2)
                .setUserId(userId)
                .build();
        List<UserExam> userExams = new ArrayList<>();
        userExams.add(userExam1);
        userExams.add(userExam2);
        IDaoUserExam daoUserExam = mock(IDaoUserExam.class);
        when(factory.getDaoUserExam()).thenReturn(daoUserExam);

        service.registerUserToExams(userId, examsId);

        verify(daoUserExam, times(1)).save(userExams);
    }

    @Test
    void registerUserToExamsWithEmptyExamsIdTest() {
        Long userId = 42L;
        String[] examsId = new String[0];

        service.registerUserToExams(userId, examsId);

        verify(factory, never()).getDaoUserExam();
    }

    @Test
    void cancelRegistrationUserToExamsTest() {
        Long userId = 42L;
        String examId1 = "1";
        String examId2 = "2";
        String[] examsId = new String[]{examId1, examId2};
        UserExam userExam1 = new UserExam.Builder()
                .setExamId(1)
                .setUserId(userId)
                .build();
        UserExam userExam2 = new UserExam.Builder()
                .setExamId(2)
                .setUserId(userId)
                .build();
        List<UserExam> userExams = new ArrayList<>();
        userExams.add(userExam1);
        userExams.add(userExam2);
        IDaoUserExam daoUserExam = mock(IDaoUserExam.class);
        when(factory.getDaoUserExam()).thenReturn(daoUserExam);

        service.cancelUserRegistrationToExams(userId, examsId);

        verify(daoUserExam, times(1)).delete(userExams);
    }

    @Test
    void cancelRegistrationUserToExamsWithEmptyExamsIdTest() {
        Long userId = 42L;
        String[] examsId = new String[0];

        service.cancelUserRegistrationToExams(userId, examsId);

        verify(factory, never()).getDaoUserExam();
    }
}
