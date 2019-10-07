package ua.company.training.kukuruza.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.company.training.kukuruza.persistence.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.persistence.dao.IDaoUser;
import ua.company.training.kukuruza.persistence.entity.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckStatusServiceTest {
    @InjectMocks
    private CheckStatusService service;
    @Mock
    private AbstractDaoFactory factory;
    @Mock
    private IDaoUser daoUser;

    @Test
    void getUpdatedUserTest() {
        Long id = 1L;
        User user = new User.Builder()
                .setId(id)
                .build();
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.find(id)).thenReturn(Optional.of(user));

        User updatedUser = service.getUpdatedUser(id);

        assertEquals(id, updatedUser.getId());
    }

    @Test
    void getUpdatedUserWithWrongIdTest() {
        Long wrongId = 1L;
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.find(wrongId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getUpdatedUser(wrongId));
    }
}
