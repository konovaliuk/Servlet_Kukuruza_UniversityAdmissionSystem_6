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
    private ExamService examService;
    private RegistrationService registrationService;
    private ResultService resultService;

    private ServiceFactory(AbstractDaoFactory factory) {
        authenticationService = new AuthenticationService(factory);
        examService = new ExamService(factory);
        registrationService = new RegistrationService(factory);
        resultService = new ResultService(factory);
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

    public ExamService getExamService() {
        return examService;
    }

    public RegistrationService getRegistrationService() {
        return registrationService;
    }

    public ResultService getResultService() {
        return resultService;
    }
}
