package ua.company.training.kukuruza.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.entity.UserStatus;
import ua.company.training.kukuruza.persistenceException.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStatusMapper {
    private static final Logger LOGGER = LogManager.getLogger(UserStatusMapper.class);
    private static final String ID = "id";
    private static final String STATUS = "status";

    public static UserStatus map(ResultSet rs) {
        try {
            return new UserStatus.Builder()
                    .setId(rs.getInt(ID))
                    .setStatus(rs.getString(STATUS))
                    .build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to UserStatus", e);
            throw new MapperException(e);
        }
    }
}
