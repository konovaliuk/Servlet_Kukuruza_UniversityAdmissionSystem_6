package ua.company.training.kukuruza.persistence.dao.mapper;

import ua.company.training.kukuruza.persistence.entity.EducationOption;
import ua.company.training.kukuruza.persistence.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EducationOptionMapper {
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
            throw new MapperException(e);
        }
    }
}
