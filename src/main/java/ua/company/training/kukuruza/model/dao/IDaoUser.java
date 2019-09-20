package ua.company.training.kukuruza.model.dao;

import ua.company.training.kukuruza.model.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IDaoUser extends IDaoGeneric<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByFirstNameAndSecondName(String firstName, String secondName);

    List<User> findByIdSet(Set<Long> usersId);

    Optional<User> findByLogin(String login);

    Optional<User> findByPassportCode(String passportCode);
}
