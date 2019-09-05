package ua.epam.training.kukuruza.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.epam.training.kukuruza.model.entity.UserStatus;
import ua.epam.training.kukuruza.model.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStatusMapper {
    private static final Logger LOGGER = LogManager.getLogger(UserStatusMapper.class);
    private static final String ID = "id";
    private static final String STATUS = "status";

    public static UserStatus map(ResultSet rs) {
        UserStatus.Builder builder = new UserStatus.Builder();
        try {
            builder.setId(rs.getInt(ID))
                    .setStatus(rs.getString(STATUS));
            return builder.build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to UserStatus", e);
            throw new MapperException(e);
        }
    }
}
