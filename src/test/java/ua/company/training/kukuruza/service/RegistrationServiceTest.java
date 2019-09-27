package ua.company.training.kukuruza.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.company.training.kukuruza.util.UserStatus;
import ua.company.training.kukuruza.util.UserType;
import ua.company.training.kukuruza.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.dao.IDaoUser;
import ua.company.training.kukuruza.entity.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    @InjectMocks
    private RegistrationService service;
    @Mock
    private AbstractDaoFactory factory;
    @Mock
    private IDaoUser daoUser;

    private User user;
    private String login = "login";
    private String email = "user@test.test";
    private String passportCode = "xx101010";
    private Integer userTypeId = UserType.STUDENT.getId();
    private Integer userStatusId = UserStatus.UNKNOWN.getId();

    @BeforeEach
    void setUp() {
        user = new User.Builder()
                .setLogin(login)
                .setEmail(email)
                .setPassportCode(passportCode)
                .build();
    }

    @Test
    void registerWithValidParametersTest() {
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.findByLogin(login)).thenReturn(Optional.empty());
        when(daoUser.findByEmail(email)).thenReturn(Optional.empty());
        when(daoUser.findByPassportCode(passportCode)).thenReturn(Optional.empty());

        service.register(user);

        verify(daoUser, times(1)).save(user);
        assertEquals(userTypeId, user.getUserTypeId());
        assertEquals(userStatusId, user.getUserStatusId());
    }

    @Test
    void registerWithSameLoginTest() {
        User existingUserWithSameLogin = new User.Builder()
                .setLogin(login)
                .build();
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.findByLogin(login)).thenReturn(Optional.of(existingUserWithSameLogin));

        ServiceException e = assertThrows(ServiceException.class, () -> service.register(user));
        assertEquals("User with the same login is already exist. Please choose another one.", e.getMessage());
        verify(daoUser, never()).save(user);
    }

    @Test
    void registerWithSameEmailTest() {
        User existingUserWithSameEmail = new User.Builder()
                .setEmail(email)
                .build();
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.findByLogin(login)).thenReturn(Optional.empty());
        when(daoUser.findByEmail(email)).thenReturn(Optional.of(existingUserWithSameEmail));

        ServiceException e = assertThrows(ServiceException.class, () -> service.register(user));
        assertEquals("User with the same email is already exist. Please choose another one.", e.getMessage());
        verify(daoUser, never()).save(user);
    }

    @Test
    void registerWithSamePassportCodeTest() {
        User existingUserWithSamePassportCode = new User.Builder()
                .setPassportCode(passportCode)
                .build();
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.findByLogin(login)).thenReturn(Optional.empty());
        when(daoUser.findByEmail(email)).thenReturn(Optional.empty());
        when(daoUser.findByPassportCode(passportCode)).thenReturn(Optional.of(existingUserWithSamePassportCode));

        ServiceException e = assertThrows(ServiceException.class, () -> service.register(user));
        assertEquals("User with the same passport code is already exist.", e.getMessage());
        verify(daoUser, never()).save(user);
    }

    @Test
    void registerCaseSensitiveTest() {
        String login = "lOGin";
        String email = "eMaiL";
        String passportCode = "paSSpOrtCode";
        User user = new User.Builder()
                .setLogin(login)
                .setEmail(email)
                .setPassportCode(passportCode)
                .build();

        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.findByLogin(login.toLowerCase())).thenReturn(Optional.empty());
        when(daoUser.findByEmail(email.toLowerCase())).thenReturn(Optional.empty());
        when(daoUser.findByPassportCode(passportCode.toLowerCase())).thenReturn(Optional.empty());

        service.register(user);

        assertEquals(login.toLowerCase(), user.getLogin());
        assertEquals(email.toLowerCase(), user.getEmail());
        assertEquals(passportCode.toLowerCase(), user.getPassportCode());
    }
}
