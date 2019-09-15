package ua.epam.training.kukuruza.model.dao;

import ua.epam.training.kukuruza.model.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IDaoUser extends IDaoGeneric<User, Long> {
    Optional<User> getByEmail(String email);
    List<User> getByIdSet(Set<Long> usersId);
    Optional<User> getByLogin(String login);
    Optional<User> getByPassportCode(String passportCode);
}
