package ua.epam.training.kukuruza.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.epam.training.kukuruza.model.entity.Exam;
import ua.epam.training.kukuruza.model.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamMapper {
    private static final Logger LOGGER = LogManager.getLogger(ExamMapper.class);
    private static final String ID = "id";
    private static final String DATE = "date";
    private static final String ADDRESS = "address";
    private static final String SUBJECT_ID = "subject_id";

    public static Exam map(ResultSet rs) {
        Exam.Builder builder = new Exam.Builder();
        try {
            builder.setId(rs.getInt(ID))
                    .setDate(rs.getTimestamp(DATE))
                    .setAddress(rs.getString(ADDRESS))
                    .setSubjectId(rs.getInt(SUBJECT_ID));
            return builder.build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to Exam", e);
            throw new MapperException(e);
        }
    }
}
