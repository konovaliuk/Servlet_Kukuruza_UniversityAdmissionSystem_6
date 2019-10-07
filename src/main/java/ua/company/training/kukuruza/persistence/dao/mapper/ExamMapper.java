package ua.company.training.kukuruza.persistence.dao.mapper;

import ua.company.training.kukuruza.persistence.entity.Exam;
import ua.company.training.kukuruza.persistence.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamMapper {
    private static final String ID = "id";
    private static final String DATE = "date";
    private static final String ADDRESS = "address";
    private static final String SUBJECT_ID = "subject_id";

    public static Exam map(ResultSet rs) {
        try {
            return new Exam.Builder()
                    .setId(rs.getInt(ID))
                    .setDate(rs.getTimestamp(DATE))
                    .setAddress(rs.getString(ADDRESS))
                    .setSubjectId(rs.getInt(SUBJECT_ID))
                    .build();
        } catch (SQLException e) {
            throw new MapperException(e);
        }
    }
}
