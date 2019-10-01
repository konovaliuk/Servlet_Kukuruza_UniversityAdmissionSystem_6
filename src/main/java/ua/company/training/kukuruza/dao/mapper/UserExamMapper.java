package ua.company.training.kukuruza.dao.mapper;

import ua.company.training.kukuruza.entity.UserExam;
import ua.company.training.kukuruza.persistenceException.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserExamMapper {
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
            throw new MapperException(e);
        }
    }
}
