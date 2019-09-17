package ua.company.training.kukuruza.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.model.entity.SpecialtySubject;
import ua.company.training.kukuruza.model.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialtySubjectMapper {
    private static final Logger LOGGER = LogManager.getLogger(SpecialtySubjectMapper.class);
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
            LOGGER.error("Can't map ResultSet to SpecialtySubject", e);
            throw new MapperException(e);
        }
    }
}
