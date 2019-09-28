package ua.company.training.kukuruza.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.entity.UserType;
import ua.company.training.kukuruza.persistenceException.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTypeMapper {
    private static final Logger LOGGER = LogManager.getLogger(UserTypeMapper.class);
    private static final String ID = "id";
    private static final String USER_ROLE = "user_role";

    public static UserType map(ResultSet rs) {
        try {
            return new UserType.Builder()
                    .setId(rs.getInt(ID))
                    .setUserRole(rs.getString(USER_ROLE))
                    .build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to UserType", e);
            throw new MapperException(e);
        }
    }
}