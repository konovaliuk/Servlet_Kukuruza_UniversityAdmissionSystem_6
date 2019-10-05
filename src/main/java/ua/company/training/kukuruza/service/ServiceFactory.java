package ua.company.training.kukuruza.service;

import ua.company.training.kukuruza.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.dao.daoMySql.MySqlDaoFactory;

public class ServiceFactory {
    private static final AbstractDaoFactory FACTORY = MySqlDaoFactory.getInstance();
    private static final AdminService ADMIN_SERVICE = new AdminService(FACTORY);
    private static final AuthenticationService AUTHENTICATION_SERVICE = new AuthenticationService(FACTORY);
    private static final CheckStatusService CHECK_STATUS_SERVICE = new CheckStatusService(FACTORY);
    private static final EducationService EDUCATION_SERVICE = new EducationService(FACTORY);
    private static final ExamService EXAM_SERVICE = new ExamService(FACTORY);
    private static final RegistrationService REGISTRATION_SERVICE = new RegistrationService(FACTORY);
    private static final ResultService RESULT_SERVICE = new ResultService(FACTORY);
    private static final SummaryRatingService SUMMARY_RATING_SERVICE = new SummaryRatingService(FACTORY);

    private ServiceFactory() {
    }

    public static AdminService getAdminService() {
        return ADMIN_SERVICE;
    }

    public static AuthenticationService getAuthenticationService() {
        return AUTHENTICATION_SERVICE;
    }

    public static CheckStatusService getCheckStatusService() {
        return CHECK_STATUS_SERVICE;
    }

    public static EducationService getEducationService() {
        return EDUCATION_SERVICE;
    }

    public static ExamService getExamService() {
        return EXAM_SERVICE;
    }

    public static RegistrationService getRegistrationService() {
        return REGISTRATION_SERVICE;
    }

    public static ResultService getResultService() {
        return RESULT_SERVICE;
    }

    public static SummaryRatingService getSummaryRatingService() {
        return SUMMARY_RATING_SERVICE;
    }
}
