package ua.company.training.kukuruza.util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class AttributeNames {
    public static final String AVAILABLE_EXAMS;
    public static final String CHOSEN_SPECIALTY;
    public static final String FIRST_NAME;
    public static final String NOT_AVAILABLE_SPECIALTY;
    public static final String REGISTRATION_ERROR;
    public static final String REQUIRED_SUBJECTS;
    public static final String SECOND_NAME;
    public static final String SIGN_IN_ERROR;
    public static final String SPECIALTIES;
    public static final String SPECIALTY_NAME;
    public static final String STUDENT_LIMIT;
    public static final String SUBJECT_ID_TO_SUBJECT_NAME;
    public static final String SUBJECTS;
    public static final String SUCCESS;
    public static final String UNIVERSITIES;
    public static final String UNIVERSITY_NAME;
    public static final String USER;
    public static final String USER_EXAMS;
    public static final String USER_GRADES;
    public static final String USER_ID_TO_RATING;
    public static final String USER_ID_TO_USER;
    public static final String USERS;

    static {
        Properties properties = new Properties();
        String fileName = "attributeName.properties";
        try {
            properties.load(Objects.requireNonNull(AttributeNames.class.getClassLoader().getResourceAsStream(fileName)));
            AVAILABLE_EXAMS = properties.getProperty("availableExams");
            CHOSEN_SPECIALTY = properties.getProperty("chosenSpecialty");
            FIRST_NAME = properties.getProperty("firstName");
            NOT_AVAILABLE_SPECIALTY = properties.getProperty("notAvailableSpecialty");
            REGISTRATION_ERROR = properties.getProperty("registrationError");
            REQUIRED_SUBJECTS = properties.getProperty("requiredSubjects");
            SECOND_NAME = properties.getProperty("secondName");
            SIGN_IN_ERROR = properties.getProperty("signInError");
            SPECIALTIES = properties.getProperty("specialties");
            SPECIALTY_NAME = properties.getProperty("specialtyName");
            STUDENT_LIMIT = properties.getProperty("studentLimit");
            SUBJECT_ID_TO_SUBJECT_NAME = properties.getProperty("subjectIdToSubjectName");
            SUBJECTS = properties.getProperty("subjects");
            SUCCESS = properties.getProperty("success");
            UNIVERSITIES = properties.getProperty("universities");
            UNIVERSITY_NAME = properties.getProperty("universityName");
            USER = properties.getProperty("user");
            USER_EXAMS = properties.getProperty("userExams");
            USER_GRADES = properties.getProperty("userGrades");
            USER_ID_TO_RATING = properties.getProperty("userIdToRating");
            USER_ID_TO_USER = properties.getProperty("userIdToUser");
            USERS = properties.getProperty("users");
        } catch (IOException e) {
            throw new RuntimeException("Can't load " + fileName, e);
        }
    }
}
