package ua.epam.training.kukuruza.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.epam.training.kukuruza.model.entity.User;
import ua.epam.training.kukuruza.model.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    private static final Logger LOGGER = LogManager.getLogger(UserMapper.class);
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "first_name";
    private static final String SECOND_NAME = "second_name";
    private static final String GENDER = "gender";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String PASSPORT_CODE = "passport_code";
    private static final String USER_TYPE_ID = "user_type_id";
    private static final String USER_STATUS_ID = "user_status_id";

    public static User map(ResultSet rs) {
        try {
            return new User.Builder()
                    .setId(rs.getLong(ID))
                    .setLogin(rs.getString(LOGIN))
                    .setPassword(rs.getString(PASSWORD))
                    .setFirstName(rs.getString(FIRST_NAME))
                    .setSecondName(rs.getString(SECOND_NAME))
                    .setGender(rs.getString(GENDER))
                    .setEmail(rs.getString(EMAIL))
                    .setPhone(rs.getString(PHONE))
                    .setPassportCode(rs.getString(PASSPORT_CODE))
                    .setUserTypeId(rs.getInt(USER_TYPE_ID))
                    .setUserStatusId(rs.getInt(USER_STATUS_ID))
                    .build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to User", e);
            throw new MapperException(e);
        }
    }
}
