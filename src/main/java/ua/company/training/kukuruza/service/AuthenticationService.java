package ua.company.training.kukuruza.service;

import ua.company.training.kukuruza.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.entity.User;

import java.util.Objects;
import java.util.Optional;

public class AuthenticationService {
    private AbstractDaoFactory factory;

    public AuthenticationService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public User signIn(String login, String password) {
        if (Objects.isNull(login) || Objects.isNull(password))
            throw new ServiceException("Please fill all of the fields of the form!");

        Optional<User> user = factory.getDaoUser().findByLogin(login);
        return user.filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new ServiceException("Oops, wrong login or password, try again!"));
    }
}
