package ua.epam.training.kukuruza.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.epam.training.kukuruza.model.entity.Grade;
import ua.epam.training.kukuruza.model.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeMapper {
    private static final Logger LOGGER = LogManager.getLogger(GradeMapper.class);
    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String SUBJECT_ID = "subject_id";
    private static final String GRADE = "grade";

    public static Grade map(ResultSet rs) {
        Grade.Builder builder = new Grade.Builder();
        try {
            builder.setId(rs.getLong(ID))
                    .setUserId(rs.getLong(USER_ID))
                    .setSubjectId(rs.getInt(SUBJECT_ID))
                    .setGrade(rs.getInt(GRADE));
            return builder.build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to Grade", e);
            throw new MapperException(e);
        }
    }
}
