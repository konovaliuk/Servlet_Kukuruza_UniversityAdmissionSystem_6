package ua.company.training.kukuruza.persistence.dao;

import ua.company.training.kukuruza.persistence.entity.User;

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
