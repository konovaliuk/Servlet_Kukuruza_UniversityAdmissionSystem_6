package ua.epam.training.kukuruza.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.epam.training.kukuruza.model.entity.SpecialtyUniversityLimit;
import ua.epam.training.kukuruza.model.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialtyUniversityLimitMapper {
    private static final Logger LOGGER = LogManager.getLogger(SpecialtyUniversityLimitMapper.class);
    private static final String ID = "id";
    private static final String UNIVERSITY_ID = "university_id";
    private static final String SPECIALTY_ID = "specialty_id";
    private static final String STUDENT_LIMIT = "student_limit";

    public static SpecialtyUniversityLimit map(ResultSet rs) {
        SpecialtyUniversityLimit.Builder builder = new SpecialtyUniversityLimit.Builder();
        try {
            builder.setId(rs.getLong(ID))
                    .setUniversityId(rs.getInt(UNIVERSITY_ID))
                    .setSpecialtyId(rs.getInt(SPECIALTY_ID))
                    .setStudentLimit(rs.getInt(STUDENT_LIMIT));
            return builder.build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to SpecialtyUniversityLimit", e);
            throw new MapperException(e);
        }
    }
}
