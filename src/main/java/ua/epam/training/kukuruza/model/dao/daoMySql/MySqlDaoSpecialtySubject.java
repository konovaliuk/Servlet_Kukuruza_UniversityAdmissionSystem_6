package ua.epam.training.kukuruza.model.dao.daoMySql;

import ua.epam.training.kukuruza.model.connection.IConnectionFactory;
import ua.epam.training.kukuruza.model.dao.IDaoSpecialtySubject;
import ua.epam.training.kukuruza.model.dao.mapper.SpecialtySubjectMapper;
import ua.epam.training.kukuruza.model.entity.SpecialtySubject;

import java.util.List;
import java.util.Optional;

public class MySqlDaoSpecialtySubject implements IDaoSpecialtySubject {
    private static final String GET_SPECIALTY_SUBJECT_BY_ID_SQL = "SELECT * FROM specialty_subject WHERE id = ?";
    private static final String GET_ALL_SPECIALTY_SUBJECT_SQL = "SELECT * FROM specialty_subject";
    private static final String INSERT_SPECIALTY_SUBJECT_SQL = "INSERT INTO specialty_subject VALUES (NULL, ?, ?)";
    private static final String UPDATE_SPECIALTY_SUBJECT_SQL = "UPDATE specialty_subject SET " +
            "specialty_id = ?, subject_id = ? WHERE id = ?";
    private static final String DELETE_SPECIALTY_SUBJECT_SQL = "DELETE FROM specialty_subject WHERE id = ?";
    private MySqlDaoHelper helper = MySqlDaoHelper.getInstance();
    private IConnectionFactory factory;

    public MySqlDaoSpecialtySubject(IConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<SpecialtySubject> get(Long id) {
        return helper.get(GET_SPECIALTY_SUBJECT_BY_ID_SQL, SpecialtySubjectMapper::map, id);
    }

    @Override
    public List<SpecialtySubject> getAll() {
        return helper.executeSelectQuery(GET_ALL_SPECIALTY_SUBJECT_SQL, SpecialtySubjectMapper::map);
    }

    @Override
    public Long save(SpecialtySubject entity) {
        Long autoGeneratedId = helper.save(INSERT_SPECIALTY_SUBJECT_SQL, Long.class,
                entity.getSpecialtyId(), entity.getSubjectId());
        entity.setId(autoGeneratedId);
        return autoGeneratedId;
    }

    @Override
    public void update(SpecialtySubject entity) {
        helper.update(UPDATE_SPECIALTY_SUBJECT_SQL, entity.getSpecialtyId(), entity.getSubjectId(), entity.getId());
    }

    @Override
    public boolean delete(Long id) {
        return helper.delete(DELETE_SPECIALTY_SUBJECT_SQL, id);
    }
}
