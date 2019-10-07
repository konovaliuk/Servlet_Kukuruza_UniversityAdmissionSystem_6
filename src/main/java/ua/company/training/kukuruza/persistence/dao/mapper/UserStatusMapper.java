package ua.company.training.kukuruza.persistence.dao.mapper;

import ua.company.training.kukuruza.persistence.entity.UserStatus;
import ua.company.training.kukuruza.persistence.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStatusMapper {
    private static final String ID = "id";
    private static final String STATUS = "status";

    public static UserStatus map(ResultSet rs) {
        try {
            return new UserStatus.Builder()
                    .setId(rs.getInt(ID))
                    .setStatus(rs.getString(STATUS))
                    .build();
        } catch (SQLException e) {
            throw new MapperException(e);
        }
    }
}
