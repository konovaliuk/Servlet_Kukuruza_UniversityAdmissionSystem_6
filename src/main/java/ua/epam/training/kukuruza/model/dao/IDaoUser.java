package ua.epam.training.kukuruza.model.dao;

import ua.epam.training.kukuruza.model.entity.User;

import java.util.Optional;

public interface IDaoUser extends IDaoGeneric<User, Long> {
    Optional<User> getByEmail(String email);
    Optional<User> getByLogin(String login);
    Optional<User> getByPassportCode(String passportCode);
}
