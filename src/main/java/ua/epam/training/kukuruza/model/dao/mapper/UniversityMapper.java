package ua.epam.training.kukuruza.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.epam.training.kukuruza.model.entity.University;
import ua.epam.training.kukuruza.model.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UniversityMapper {
    private static final Logger LOGGER = LogManager.getLogger(UniversityMapper.class);
    private static final String ID = "id";
    private static final String NAME = "name";

    public static University map(ResultSet rs) {
        University.Builder builder = new University.Builder();
        try {
            builder.setId(rs.getInt(ID))
                    .setName(rs.getString(NAME));
            return builder.build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to University", e);
            throw new MapperException(e);
        }
    }
}
