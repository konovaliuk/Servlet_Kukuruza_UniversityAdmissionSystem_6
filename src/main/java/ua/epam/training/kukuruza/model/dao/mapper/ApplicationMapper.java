package ua.epam.training.kukuruza.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.epam.training.kukuruza.model.entity.Application;
import ua.epam.training.kukuruza.model.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationMapper {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationMapper.class);
    private static final String USER_ID = "user_id";
    private static final String RATING = "rating";
    private static final String SPECIALTY_UNIVERSITY_LIMIT_ID = "specialty_university_limit_id";

    public static Application map(ResultSet rs) {
        Application.Builder builder = new Application.Builder();
        try {
            builder.setUserId(rs.getLong(USER_ID))
                    .setRating(rs.getInt(RATING))
                    .setSpecialtyUniversityLimitId(rs.getLong(SPECIALTY_UNIVERSITY_LIMIT_ID));
            return builder.build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to Application", e);
            throw new MapperException(e);
        }
    }
}
