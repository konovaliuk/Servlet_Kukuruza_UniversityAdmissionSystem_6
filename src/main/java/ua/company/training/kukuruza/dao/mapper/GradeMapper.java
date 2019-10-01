package ua.company.training.kukuruza.dao.mapper;

import ua.company.training.kukuruza.entity.Grade;
import ua.company.training.kukuruza.persistenceException.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeMapper {
    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String SUBJECT_ID = "subject_id";
    private static final String RESULT = "result";

    public static Grade map(ResultSet rs) {
        try {
            return new Grade.Builder()
                    .setId(rs.getLong(ID))
                    .setUserId(rs.getLong(USER_ID))
                    .setSubjectId(rs.getInt(SUBJECT_ID))
                    .setResult(rs.getInt(RESULT))
                    .build();
        } catch (SQLException e) {
            throw new MapperException(e);
        }
    }
}
