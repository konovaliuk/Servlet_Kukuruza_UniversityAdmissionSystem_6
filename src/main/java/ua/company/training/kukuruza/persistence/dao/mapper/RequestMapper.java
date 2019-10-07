package ua.company.training.kukuruza.persistence.dao.mapper;

import ua.company.training.kukuruza.persistence.entity.Request;
import ua.company.training.kukuruza.persistence.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestMapper {
    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String RATING = "rating";
    private static final String EDUCATION_OPTION_ID = "education_option_id";

    public static Request map(ResultSet rs) {
        try {
            return new Request.Builder()
                    .setId(rs.getLong(ID))
                    .setRating(rs.getInt(RATING))
                    .setUserId(rs.getLong(USER_ID))
                    .setEducationOptionId(rs.getLong(EDUCATION_OPTION_ID))
                    .build();
        } catch (SQLException e) {
            throw new MapperException(e);
        }
    }
}
