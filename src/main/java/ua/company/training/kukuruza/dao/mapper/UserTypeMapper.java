package ua.company.training.kukuruza.dao.mapper;

import ua.company.training.kukuruza.entity.UserType;
import ua.company.training.kukuruza.persistenceException.MapperException;

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
