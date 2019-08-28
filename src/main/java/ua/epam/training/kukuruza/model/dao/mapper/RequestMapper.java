package ua.epam.training.kukuruza.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.epam.training.kukuruza.model.entity.Request;
import ua.epam.training.kukuruza.model.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestMapper {
    private static final Logger LOGGER = LogManager.getLogger(RequestMapper.class);
    private static final String USER_ID = "user_id";
    private static final String RATING = "rating";
    private static final String EDUCATION_OPTION_ID = "education_option_id";

    public static Request map(ResultSet rs) {
        Request.Builder builder = new Request.Builder();
        try {
            builder.setUserId(rs.getLong(USER_ID))
                    .setRating(rs.getInt(RATING))
                    .setEducationOptionId(rs.getLong(EDUCATION_OPTION_ID));
            return builder.build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to Request", e);
            throw new MapperException(e);
        }
    }
}
