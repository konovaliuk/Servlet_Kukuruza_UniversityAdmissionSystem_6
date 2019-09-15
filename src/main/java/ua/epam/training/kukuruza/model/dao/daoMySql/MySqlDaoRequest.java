package ua.epam.training.kukuruza.model.dao.daoMySql;

import ua.epam.training.kukuruza.model.connection.IConnectionFactory;
import ua.epam.training.kukuruza.model.dao.IDaoRequest;
import ua.epam.training.kukuruza.model.dao.mapper.RequestMapper;
import ua.epam.training.kukuruza.model.entity.Request;

import java.util.List;
import java.util.Optional;

public class MySqlDaoRequest implements IDaoRequest {
    private static final String GET_REQUEST_BY_USER_ID_SQL = "SELECT * FROM request WHERE user_id = ?";
    private static final String GET_ALL_REQUESTS_SQL = "SELECT * FROM request";
    private static final String INSERT_REQUEST_SQL = "INSERT INTO request VALUES (?, ?, ?)";
    private static final String UPDATE_REQUEST_SQL = "UPDATE request SET " +
            "rating = ?, education_option_id = ? WHERE user_id = ?";
    private static final String DELETE_REQUEST_SQL = "DELETE FROM request WHERE user_id = ?";
    private MySqlDaoHelper helper = MySqlDaoHelper.getInstance();
    private IConnectionFactory factory;

    public MySqlDaoRequest(IConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<Request> get(Long id) {
        return helper.get(GET_REQUEST_BY_USER_ID_SQL, RequestMapper::map, id);
    }

    @Override
    public List<Request> getAll() {
        return helper.getList(GET_ALL_REQUESTS_SQL, RequestMapper::map);
    }

    @Override
    public Optional<Request> getByUserId(Long userId) {
        return get(userId);
    }

    @Override
    public Long save(Request entity) {
        helper.saveWithoutAutoKey(INSERT_REQUEST_SQL,
                entity.getUserId(), entity.getRating(), entity.getEducationOptionId());
        return entity.getUserId();
    }

    @Override
    public void update(Request entity) {
        helper.update(UPDATE_REQUEST_SQL, entity.getRating(), entity.getEducationOptionId(), entity.getUserId());
    }

    @Override
    public boolean delete(Long id) {
        return helper.delete(DELETE_REQUEST_SQL, id);
    }

    @Override
    public boolean deleteByUserId(Long userId) {
        return delete(userId);
    }
}
