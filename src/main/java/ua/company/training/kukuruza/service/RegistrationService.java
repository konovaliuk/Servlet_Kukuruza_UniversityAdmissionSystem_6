package ua.company.training.kukuruza.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.entity.User;
import ua.company.training.kukuruza.util.UserStatus;
import ua.company.training.kukuruza.util.UserType;
import ua.company.training.kukuruza.dao.IDaoUser;

import java.util.Optional;

public class RegistrationService {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationService.class);
    private AbstractDaoFactory factory;

    RegistrationService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public void register(User user) {
        LOGGER.info("Try to register new user");
        user.setLogin(user.getLogin().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassportCode(user.getPassportCode().toLowerCase());
        user.setUserTypeId(UserType.STUDENT.getId());
        user.setUserStatusId(UserStatus.UNKNOWN.getId());

        IDaoUser daoUser = factory.getDaoUser();

        Optional<User> userWithSameLogin = daoUser.findByLogin(user.getLogin());
        if (userWithSameLogin.isPresent())
            throw new ServiceException("User with the same login is already exist. Please choose another one.");

        Optional<User> userWithSameEmail = daoUser.findByEmail(user.getEmail());
        if (userWithSameEmail.isPresent())
            throw new ServiceException("User with the same email is already exist. Please choose another one.");

        Optional<User> userWithSamePassportCode = daoUser.findByPassportCode(user.getPassportCode());
        if (userWithSamePassportCode.isPresent())
            throw new ServiceException("User with the same passport code is already exist.");

        LOGGER.info("User validation successfully done. Try to save new user");
        daoUser.save(user);
    }
}
