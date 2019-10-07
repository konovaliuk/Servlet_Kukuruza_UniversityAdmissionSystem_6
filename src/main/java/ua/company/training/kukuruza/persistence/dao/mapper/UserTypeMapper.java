package ua.company.training.kukuruza.persistence.dao.mapper;

import ua.company.training.kukuruza.persistence.entity.UserType;
import ua.company.training.kukuruza.persistence.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTypeMapper {
    private static final String ID = "id";
    private static final String USER_ROLE = "user_role";

    public static UserType map(ResultSet rs) {
        try {
            return new UserType.Builder()
                    .setId(rs.getInt(ID))
                    .setUserRole(rs.getString(USER_ROLE))
                    .build();
        } catch (SQLException e) {
            throw new MapperException(e);
        }
    }
}
