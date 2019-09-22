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
