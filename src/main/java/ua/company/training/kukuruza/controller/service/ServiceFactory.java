package ua.company.training.kukuruza.controller.service;

import ua.company.training.kukuruza.model.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.model.dao.daoMySql.MySqlDaoFactory;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory(MySqlDaoFactory.getInstance());
    private AdminService adminService;
    private AuthenticationService authenticationService;
    private CheckStatusService checkStatusService;
    private EducationService educationService;
    private ExamService examService;
    private RegistrationService registrationService;
    private ResultService resultService;
    private SummaryRatingService summaryRatingService;

    private ServiceFactory(AbstractDaoFactory factory) {
        adminService = new AdminService(factory);
        authenticationService = new AuthenticationService(factory);
        checkStatusService = new CheckStatusService(factory);
        educationService = new EducationService(factory);
        examService = new ExamService(factory);
        registrationService = new RegistrationService(factory);
        resultService = new ResultService(factory);
        summaryRatingService = new SummaryRatingService(factory);
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public CheckStatusService getCheckStatusService() {
        return checkStatusService;
    }

    public EducationService getEducationService() {
        return educationService;
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

    public SummaryRatingService getSummaryRatingService() {
        return summaryRatingService;
    }
}
