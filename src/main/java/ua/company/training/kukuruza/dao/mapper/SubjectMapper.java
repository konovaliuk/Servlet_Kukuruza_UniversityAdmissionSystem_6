package ua.company.training.kukuruza.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.entity.Subject;
import ua.company.training.kukuruza.persistenceException.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper {
    private static final Logger LOGGER = LogManager.getLogger(SubjectMapper.class);
    private static final String ID = "id";
    private static final String NAME = "name";

    public static Subject map(ResultSet rs) {
        try {
            return new Subject.Builder()
                    .setId(rs.getInt(ID))
                    .setName(rs.getString(NAME))
                    .build();
        } catch (SQLException e) {
            LOGGER.error("Can't map ResultSet to Subject", e);
            throw new MapperException(e);
        }
    }
}
