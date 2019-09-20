package ua.company.training.kukuruza.model.dao.daoMySql;

import ua.company.training.kukuruza.model.connection.IConnectionFactory;
import ua.company.training.kukuruza.model.dao.IDaoSpecialty;
import ua.company.training.kukuruza.model.dao.mapper.SpecialtyMapper;
import ua.company.training.kukuruza.model.entity.Specialty;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MySqlDaoSpecialty implements IDaoSpecialty {
    private static final String GET_SPECIALTY_BY_ID_SQL = "SELECT * FROM specialty WHERE id = ?";
    private static final String GET_ALL_SPECIALTIES_SQL = "SELECT * FROM specialty";
    private static final String INSERT_SPECIALTY_SQL = "INSERT INTO specialty VALUES (NULL, ?)";
    private static final String UPDATE_SPECIALTY_SQL = "UPDATE specialty SET name = ? WHERE id = ?";
    private static final String DELETE_SPECIALTY_SQL = "DELETE FROM specialty WHERE id = ?";
    private MySqlDaoHelper helper = MySqlDaoHelper.getInstance();
    private IConnectionFactory factory;

    public MySqlDaoSpecialty(IConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<Specialty> find(Integer id) {
        return helper.get(GET_SPECIALTY_BY_ID_SQL, SpecialtyMapper::map, id);
    }

    @Override
    public List<Specialty> findAll() {
        return helper.getList(GET_ALL_SPECIALTIES_SQL, SpecialtyMapper::map);
    }

    @Override
    public List<Specialty> findByIdSet(Set<Integer> specialtiesId) {
        if (specialtiesId.isEmpty())
            return Collections.emptyList();
        String sql = helper.buildSql(new StringBuilder("SELECT * FROM specialty WHERE id IN("), specialtiesId);
        return helper.getList(sql, SpecialtyMapper::map);
    }

    @Override
    public Integer save(Specialty entity) {
        Integer autoGeneratedKey = helper.save(INSERT_SPECIALTY_SQL, entity.getName());
        entity.setId(autoGeneratedKey);
        return autoGeneratedKey;
    }

    @Override
    public void update(Specialty entity) {
        helper.update(UPDATE_SPECIALTY_SQL, entity.getName(), entity.getId());
    }

    @Override
    public boolean delete(Integer id) {
        return helper.delete(DELETE_SPECIALTY_SQL, id);
    }
}
