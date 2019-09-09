package ua.epam.training.kukuruza.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.epam.training.kukuruza.model.entity.UserExam;
import ua.epam.training.kukuruza.model.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserExamMapper {
    private static final Logger LOGGER = LogManager.getLogger(UserExamMapper.class);
    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String EXAM_ID = "exam_id";

    public static UserExam map(ResultSet rs) {
        try {
            return new UserExam.Builder()
                    .setId(rs.getLong(ID))
                    .setUserId(rs.getLong(USER_ID))
                    .setExamId(rs.getInt(EXAM_ID))
                    .build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to UserExam", e);
            throw new MapperException(e);
        }
    }
}
