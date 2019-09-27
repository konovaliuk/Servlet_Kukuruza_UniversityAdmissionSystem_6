package ua.company.training.kukuruza.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.entity.Exam;
import ua.company.training.kukuruza.persistenceException.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamMapper {
    private static final Logger LOGGER = LogManager.getLogger(ExamMapper.class);
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
            LOGGER.error("Can't map ResultSet to Exam", e);
            throw new MapperException(e);
        }
    }
}
