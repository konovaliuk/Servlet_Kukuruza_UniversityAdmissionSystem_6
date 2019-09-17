package ua.company.training.kukuruza.controller.service;

import ua.company.training.kukuruza.model.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.model.entity.User;
import ua.company.training.kukuruza.controller.util.UserStatus;
import ua.company.training.kukuruza.controller.util.UserType;
import ua.company.training.kukuruza.model.dao.IDaoUser;

import java.util.Optional;

public class RegistrationService {
    private AbstractDaoFactory factory;

    public RegistrationService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public void register(User user) {
        user.setLogin(user.getLogin().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassportCode(user.getPassportCode().toLowerCase());
        user.setUserTypeId(UserType.STUDENT.getId());
        user.setUserStatusId(UserStatus.UNKNOWN.getId());

        IDaoUser daoUser = factory.getDaoUser();

        Optional<User> userWithSameLogin = daoUser.getByLogin(user.getLogin());
        if (userWithSameLogin.isPresent())
            throw new ServiceException("User with the same login is already exist. Please choose another one.");

        Optional<User> userWithSameEmail = daoUser.getByEmail(user.getEmail());
        if (userWithSameEmail.isPresent())
            throw new ServiceException("User with the same email is already exist. Please choose another one.");

        Optional<User> userWithSamePassportCode = daoUser.getByPassportCode(user.getPassportCode());
        if (userWithSamePassportCode.isPresent())
            throw new ServiceException("User with the same passport code is already exist.");

        daoUser.save(user);
    }
}
