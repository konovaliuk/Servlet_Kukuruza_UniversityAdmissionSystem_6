package ua.company.training.kukuruza.persistence.dao.mapper;

import ua.company.training.kukuruza.persistence.entity.University;
import ua.company.training.kukuruza.persistence.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UniversityMapper {
    private static final String ID = "id";
    private static final String NAME = "name";

    public static University map(ResultSet rs) {
        try {
            return new University.Builder()
                    .setId(rs.getInt(ID))
                    .setName(rs.getString(NAME))
                    .build();
        } catch (SQLException e) {
            throw new MapperException(e);
        }
    }
}
