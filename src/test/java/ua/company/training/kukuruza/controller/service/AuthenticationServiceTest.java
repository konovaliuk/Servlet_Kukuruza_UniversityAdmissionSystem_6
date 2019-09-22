package ua.company.training.kukuruza.controller.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.company.training.kukuruza.model.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.model.dao.IDaoUser;
import ua.company.training.kukuruza.model.entity.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationService service;
    @Mock
    private AbstractDaoFactory factory;
    @Mock
    private IDaoUser daoUser;
    private String login = "login";
    private String password = "password";

    @Test
    void signInWithCorrectLoginAndPasswordTest() {
        User expected = new User.Builder()
                .setLogin(login)
                .setPassword(password)
                .build();
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.findByLogin(login)).thenReturn(Optional.of(expected));

        User actual = service.signIn(login, password);

        assertEquals(expected, actual);
    }

    @Test
    void signInWithWrongPasswordTest() {
        User expected = new User.Builder()
                .setLogin(login)
                .setPassword("anotherPassword")
                .build();
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.findByLogin(login)).thenReturn(Optional.of(expected));

        ServiceException e = assertThrows(ServiceException.class, () -> service.signIn(login, password));
        assertEquals("Oops, wrong login or password, try again!", e.getMessage());
    }

    @Test
    void signInWithWrongLoginTest() {
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.findByLogin(login)).thenReturn(Optional.empty());

        ServiceException e = assertThrows(ServiceException.class, () -> service.signIn(login, password));
        assertEquals("Oops, wrong login or password, try again!", e.getMessage());
    }

    @Test
    void signInWithNullLoginTest() {
        ServiceException e = assertThrows(ServiceException.class, () -> service.signIn(null, password));
        assertEquals("Please fill all of the fields of the form!", e.getMessage());
        verify(factory, never()).getDaoUser();
    }

    @Test
    void signInWithNullPasswordTest() {
        ServiceException e = assertThrows(ServiceException.class, () -> service.signIn(login, null));
        assertEquals("Please fill all of the fields of the form!", e.getMessage());
        verify(factory, never()).getDaoUser();
    }
}
