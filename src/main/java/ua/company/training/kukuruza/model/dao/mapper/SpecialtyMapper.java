package ua.company.training.kukuruza.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.model.entity.Specialty;
import ua.company.training.kukuruza.model.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialtyMapper {
    private static final Logger LOGGER = LogManager.getLogger(SpecialtyMapper.class);
    private static final String ID = "id";
    private static final String NAME = "name";

    public static Specialty map(ResultSet rs) {
        try {
            return new Specialty.Builder()
                    .setId(rs.getInt(ID))
                    .setName(rs.getString(NAME))
                    .build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to Specialty", e);
            throw new MapperException(e);
        }
    }
}
