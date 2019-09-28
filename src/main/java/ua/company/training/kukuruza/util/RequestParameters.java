package ua.company.training.kukuruza.util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class RequestParameters {
    public static final String COMMAND;
    public static final String EMAIL;
    public static final String EXAMS_ID;
    public static final String FIRST_NAME;
    public static final String GENDER;
    public static final String LANG;
    public static final String LOGIN;
    public static final String PAGE;
    public static final String PASSPORT_CODE;
    public static final String PASSWORD;
    public static final String PHONE;
    public static final String RESULT;
    public static final String SECOND_NAME;
    public static final String SPECIALTY_ID;
    public static final String SUBJECT_ID;
    public static final String UNIVERSITY_ID;
    public static final String USER_ID;
    public static final String USER_STATUS;

    static {
        Properties properties = new Properties();
        String fileName = "requestParameter.properties";
        try {
            properties.load(Objects.requireNonNull(RequestParameters.class.getClassLoader()
                    .getResourceAsStream(fileName)));
            COMMAND = properties.getProperty("command");
            EMAIL = properties.getProperty("email");
            EXAMS_ID = properties.getProperty("examsId");
            FIRST_NAME = properties.getProperty("firstName");
            GENDER = properties.getProperty("gender");
            LANG = properties.getProperty("lang");
            LOGIN = properties.getProperty("login");
            PAGE = properties.getProperty("page");
            PASSPORT_CODE = properties.getProperty("passportCode");
            PASSWORD = properties.getProperty("password");
            PHONE = properties.getProperty("phone");
            RESULT = properties.getProperty("result");
            SECOND_NAME = properties.getProperty("secondName");
            SPECIALTY_ID = properties.getProperty("specialtyId");
            SUBJECT_ID = properties.getProperty("subjectId");
            UNIVERSITY_ID = properties.getProperty("universityId");
            USER_ID = properties.getProperty("userId");
            USER_STATUS = properties.getProperty("userStatus");
        } catch (IOException e) {
            throw new RuntimeException("Can't load " + fileName, e);
        }
    }
}
