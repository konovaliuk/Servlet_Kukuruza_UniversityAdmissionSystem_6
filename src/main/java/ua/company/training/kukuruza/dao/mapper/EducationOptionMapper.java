package ua.company.training.kukuruza.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.entity.EducationOption;
import ua.company.training.kukuruza.persistenceException.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EducationOptionMapper {
    private static final Logger LOGGER = LogManager.getLogger(EducationOptionMapper.class);
    private static final String ID = "id";
    private static final String UNIVERSITY_ID = "university_id";
    private static final String SPECIALTY_ID = "specialty_id";
    private static final String STUDENT_LIMIT = "student_limit";

    public static EducationOption map(ResultSet rs) {
        try {
            return new EducationOption.Builder()
                    .setId(rs.getLong(ID))
                    .setUniversityId(rs.getInt(UNIVERSITY_ID))
                    .setSpecialtyId(rs.getInt(SPECIALTY_ID))
                    .setStudentLimit(rs.getInt(STUDENT_LIMIT))
                    .build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to EducationOption", e);
            throw new MapperException(e);
        }
    }
}
