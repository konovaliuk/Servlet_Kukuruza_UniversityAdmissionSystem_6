package ua.company.training.kukuruza.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.company.training.kukuruza.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.dao.IDaoGrade;
import ua.company.training.kukuruza.dao.IDaoSubject;
import ua.company.training.kukuruza.dao.IDaoUser;
import ua.company.training.kukuruza.entity.Grade;
import ua.company.training.kukuruza.entity.Subject;
import ua.company.training.kukuruza.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {
    @InjectMocks
    private AdminService service;
    @Mock
    private AbstractDaoFactory factory;

    @Test
    void findUsersTest() {
        IDaoUser daoUser = mock(IDaoUser.class);
        String firstName = "userFirstName";
        String secondName = "userSecondName";
        User user = new User.Builder()
                .setFirstName(firstName)
                .setSecondName(secondName)
                .build();
        List<User> expected = new ArrayList<>();
        expected.add(user);
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.findByFirstNameAndSecondName(firstName, secondName)).thenReturn(expected);

        List<User> actual = service.findUsers(firstName, secondName);

        assertEquals(expected, actual);
    }

    @Test
    void findUsersWithNullFirstNameTest() {
        List<User> actual = service.findUsers(null, "userSecondName");
        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    void findUsersWithNullSecondNameTest() {
        List<User> actual = service.findUsers("userFirstName", null);
        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    void findUsersWithNullFirstNameAndSecondNameTest() {
        List<User> actual = service.findUsers(null, null);
        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    void getSubjectsTest() {
        IDaoSubject daoSubject = mock(IDaoSubject.class);
        Subject subject = new Subject.Builder()
                .setId(1)
                .setName("subjectName")
                .build();
        List<Subject> expected = new ArrayList<>();
        expected.add(subject);
        when(factory.getDaoSubject()).thenReturn(daoSubject);
        when(daoSubject.findAll()).thenReturn(expected);

        List<Subject> actual = service.getSubjects();

        assertEquals(expected, actual);
    }

    @Test
    void setGradeWithExistingGradeTest() {
        IDaoGrade daoGrade = mock(IDaoGrade.class);
        Long userId = 1L;
        Integer subjectId = 1;
        Integer newResult = 100;
        Grade grade = new Grade.Builder()
                .setUserId(userId)
                .setSubjectId(subjectId)
                .setResult(50)
                .build();
        when(factory.getDaoGrade()).thenReturn(daoGrade);
        when(daoGrade.findByUserIdAndSubjectId(userId, subjectId)).thenReturn(Optional.of(grade));

        service.setGrade(userId, subjectId, newResult);

        assertEquals(newResult, grade.getResult());
        verify(daoGrade).update(grade);
    }

    @Test
    void setGradeWithNotExistingGradeTest() {
        IDaoGrade daoGrade = mock(IDaoGrade.class);
        Long userId = 1L;
        Integer subjectId = 1;
        Integer result = 100;
        Grade grade = new Grade.Builder()
                .setUserId(userId)
                .setSubjectId(subjectId)
                .setResult(result)
                .build();
        when(factory.getDaoGrade()).thenReturn(daoGrade);
        when(daoGrade.findByUserIdAndSubjectId(userId, subjectId)).thenReturn(Optional.empty());

        service.setGrade(userId, subjectId, result);

        assertEquals(result, grade.getResult());
        verify(daoGrade).save(grade);
    }

    @Test
    void setUserStatusWithExistingUserTest() {
        IDaoUser daoUser = mock(IDaoUser.class);
        Long userId = 1L;
        Integer newUserStatusId = 42;
        User user = new User.Builder()
                .setId(userId)
                .setUserStatusId(1)
                .build();
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.find(userId)).thenReturn(Optional.of(user));

        service.setUserStatus(userId, newUserStatusId);

        assertEquals(newUserStatusId, user.getUserStatusId());
        verify(daoUser).update(user);
    }

    @Test
    void setUserStatusWithNotExistingUserTest() {
        IDaoUser daoUser = mock(IDaoUser.class);
        Long wrongUserId = 1L;
        Integer newUserStatusId = 42;
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.find(wrongUserId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.setUserStatus(wrongUserId, newUserStatusId));
        verify(daoUser, never()).update(any());
    }
}
