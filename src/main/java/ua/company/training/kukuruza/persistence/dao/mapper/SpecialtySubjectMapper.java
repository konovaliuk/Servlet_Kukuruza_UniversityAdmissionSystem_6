package ua.company.training.kukuruza.persistence.dao.mapper;

import ua.company.training.kukuruza.persistence.entity.SpecialtySubject;
import ua.company.training.kukuruza.persistence.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialtySubjectMapper {
    private static final String ID = "id";
    private static final String SPECIALTY_ID = "specialty_id";
    private static final String SUBJECT_ID = "subject_id";

    public static SpecialtySubject map(ResultSet rs) {
        try {
            return new SpecialtySubject.Builder().setId(rs.getLong(ID))
                    .setSpecialtyId(rs.getInt(SPECIALTY_ID))
                    .setSubjectId(rs.getInt(SUBJECT_ID))
                    .build();
        } catch (SQLException e) {
            throw new MapperException(e);
        }
    }
}
