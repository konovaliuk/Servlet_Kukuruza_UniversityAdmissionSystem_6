package ua.epam.training.kukuruza.controller.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.epam.training.kukuruza.model.dao.AbstractDaoFactory;
import ua.epam.training.kukuruza.model.dao.daoMySql.MySqlDaoFactory;

import java.util.Objects;

public class ServiceFactory {
    private static final Logger LOGGER = LogManager.getLogger(ServiceFactory.class);
    private static ServiceFactory instance;
    private AuthenticationService authenticationService;
    private RegistrationService registrationService;

    private ServiceFactory(AbstractDaoFactory factory) {
        authenticationService = new AuthenticationService(factory);
        registrationService = new RegistrationService(factory);
    }

    public static ServiceFactory getInstance() {
        if (Objects.isNull(instance)) {
            LOGGER.debug("Begin of creating instance");
            instance = createInstance();
            LOGGER.debug("Successful creating instance");
        }
        return instance;
    }

    private static synchronized ServiceFactory createInstance() {
        if (Objects.nonNull(instance)) {
            LOGGER.debug("Another thread created instance");
            return instance;
        }
        return new ServiceFactory(MySqlDaoFactory.getInstance());
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public RegistrationService getRegistrationService() {
        return registrationService;
    }
}
