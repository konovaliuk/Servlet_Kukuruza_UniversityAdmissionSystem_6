package ua.company.training.kukuruza.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.model.entity.Request;
import ua.company.training.kukuruza.model.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestMapper {
    private static final Logger LOGGER = LogManager.getLogger(RequestMapper.class);
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
            LOGGER.error("Can't map ResultSet to Request", e);
            throw new MapperException(e);
        }
    }
}
