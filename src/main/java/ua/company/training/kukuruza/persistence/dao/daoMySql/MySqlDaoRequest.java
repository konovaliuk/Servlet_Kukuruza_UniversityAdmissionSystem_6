package ua.company.training.kukuruza.persistence.dao.daoMySql;

import ua.company.training.kukuruza.persistence.dbConnection.IConnectionFactory;
import ua.company.training.kukuruza.persistence.dao.IDaoRequest;
import ua.company.training.kukuruza.persistence.dao.mapper.RequestMapper;
import ua.company.training.kukuruza.persistence.entity.Request;

import java.util.List;
import java.util.Optional;

public class MySqlDaoRequest implements IDaoRequest {
    private static final String GET_REQUEST_BY_ID_SQL = "SELECT * FROM request WHERE id = ?";
    private static final String GET_REQUEST_BY_USER_ID_SQL = "SELECT * FROM request WHERE user_id = ?";
    private static final String GET_REQUEST_BY_EDUCATION_OPTION_ID_SQL =
            "SELECT * FROM request WHERE education_option_id = ?";
    private static final String GET_ALL_REQUESTS_SQL = "SELECT * FROM request";
    private static final String INSERT_REQUEST_SQL = "INSERT INTO request VALUES (NULL, ?, ?, ?)";
    private static final String UPDATE_REQUEST_SQL = "UPDATE request SET " +
            "rating = ?, user_id = ?, education_option_id = ? WHERE id = ?";
    private static final String DELETE_REQUEST_BY_ID_SQL = "DELETE FROM request WHERE id = ?";
    private static final String DELETE_REQUEST_BY_USER_ID_SQL = "DELETE FROM request WHERE user_id = ?";
    private MySqlDaoHelper helper = MySqlDaoHelper.getInstance();
    private IConnectionFactory factory;

    MySqlDaoRequest(IConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<Request> find(Long id) {
        return helper.get(GET_REQUEST_BY_ID_SQL, RequestMapper::map, id);
    }

    @Override
    public List<Request> findAll() {
        return helper.getList(GET_ALL_REQUESTS_SQL, RequestMapper::map);
    }

    @Override
    public List<Request> findByEducationOptionId(Long educationOptionId) {
        return helper.getList(GET_REQUEST_BY_EDUCATION_OPTION_ID_SQL, RequestMapper::map, educationOptionId);
    }

    @Override
    public Optional<Request> findByUserId(Long userId) {
        return helper.get(GET_REQUEST_BY_USER_ID_SQL, RequestMapper::map, userId);
    }

    @Override
    public Long save(Request entity) {
        Long autoGeneratedId = helper.save(INSERT_REQUEST_SQL, Long.class,
                entity.getRating(), entity.getUserId(), entity.getEducationOptionId());
        entity.setId(autoGeneratedId);
        return entity.getId();
    }

    @Override
    public void update(Request entity) {
        helper.update(UPDATE_REQUEST_SQL,
                entity.getRating(), entity.getUserId(), entity.getEducationOptionId(), entity.getId());
    }

    @Override
    public boolean delete(Long id) {
        return helper.delete(DELETE_REQUEST_BY_ID_SQL, id);
    }

    @Override
    public boolean deleteByUserId(Long userId) {
        return helper.delete(DELETE_REQUEST_BY_USER_ID_SQL, userId);
    }
}
