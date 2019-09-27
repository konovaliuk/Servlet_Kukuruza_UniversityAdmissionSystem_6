package ua.company.training.kukuruza.util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class CommandNames {
    public static final String CHANGE_SPECIALTY_COMMAND;
    public static final String CHECK_STATUS_COMMAND;
    public static final String EXAM_CANCEL_REGISTRATION_COMMAND;
    public static final String EXAM_COMMAND;
    public static final String EXAM_REGISTRATION_COMMAND;
    public static final String FIND_USER_SENDING_NOTIFICATION_COMMAND;
    public static final String FIND_USER_SETTING_GRADE_COMMAND;
    public static final String LOCALIZATION_COMMAND;
    public static final String REGISTRATION_COMMAND;
    public static final String RESULTS_COMMAND;
    public static final String SET_GRADE_COMMAND;
    public static final String SET_USER_STATUS_COMMAND;
    public static final String SIGN_IN_COMMAND;
    public static final String SIGN_OUT_COMMAND;
    public static final String SPECIALTY_SELECTION_COMMAND;
    public static final String SUBMIT_REQUEST_COMMAND;
    public static final String SUMMARY_RATING_COMMAND;
    public static final String UNIVERSITY_SELECTION_COMMAND;

    static {
        Properties properties = new Properties();
        String fileName = "command.properties";
        try {
            properties.load(Objects.requireNonNull(CommandNames.class.getClassLoader().getResourceAsStream(fileName)));
            CHANGE_SPECIALTY_COMMAND = properties.getProperty("changeSpecialty");
            CHECK_STATUS_COMMAND = properties.getProperty("checkStatus");
            EXAM_CANCEL_REGISTRATION_COMMAND = properties.getProperty("examCancelRegistration");
            EXAM_COMMAND = properties.getProperty("exam");
            EXAM_REGISTRATION_COMMAND = properties.getProperty("examRegistration");
            FIND_USER_SENDING_NOTIFICATION_COMMAND = properties.getProperty("findUserSendingNotification");
            FIND_USER_SETTING_GRADE_COMMAND = properties.getProperty("findUserSettingGrade");
            LOCALIZATION_COMMAND = properties.getProperty("localization");
            REGISTRATION_COMMAND = properties.getProperty("registration");
            RESULTS_COMMAND = properties.getProperty("results");
            SET_GRADE_COMMAND = properties.getProperty("setGrade");
            SET_USER_STATUS_COMMAND = properties.getProperty("setUserStatus");
            SIGN_IN_COMMAND = properties.getProperty("signIn");
            SIGN_OUT_COMMAND = properties.getProperty("signOut");
            SPECIALTY_SELECTION_COMMAND = properties.getProperty("specialtySelection");
            SUBMIT_REQUEST_COMMAND = properties.getProperty("submitRequest");
            SUMMARY_RATING_COMMAND = properties.getProperty("summaryRating");
            UNIVERSITY_SELECTION_COMMAND = properties.getProperty("universitySelection");
        } catch (IOException e) {
            throw new RuntimeException("Can't load " + fileName, e);
        }
    }
}
