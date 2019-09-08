package ua.epam.training.kukuruza.model.dao.daoMySql;

import ua.epam.training.kukuruza.model.connection.IConnectionFactory;
import ua.epam.training.kukuruza.model.dao.IDaoUserStatus;
import ua.epam.training.kukuruza.model.dao.mapper.UserStatusMapper;
import ua.epam.training.kukuruza.model.entity.UserStatus;

import java.util.List;
import java.util.Optional;

public class MySqlDaoUserStatus implements IDaoUserStatus {
    private static final String GET_USER_STATUS_BY_ID_SQL = "SELECT * FROM user_status WHERE id = ?";
    private static final String GET_ALL_USER_STATUSES_SQL = "SELECT * FROM user_status";
    private static final String INSERT_USER_STATUS_SQL = "INSERT INTO user_status VALUES (NULL, ?)";
    private static final String UPDATE_USER_STATUS_SQL = "UPDATE user_status SET status = ? WHERE id = ?";
    private static final String DELETE_USER_STATUS_SQL = "DELETE FROM user_status WHERE id = ?";
    private MySqlDaoHelper helper = MySqlDaoHelper.getInstance();
    private IConnectionFactory factory;

    public MySqlDaoUserStatus(IConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<UserStatus> get(Integer id) {
        return helper.get(GET_USER_STATUS_BY_ID_SQL, UserStatusMapper::map, id);
    }

    @Override
    public List<UserStatus> getAll() {
        return helper.getAll(GET_ALL_USER_STATUSES_SQL, UserStatusMapper::map);
    }

    @Override
    public Integer save(UserStatus entity) {
        Integer autoGeneratedId = helper.save(INSERT_USER_STATUS_SQL, entity.getStatus());
        entity.setId(autoGeneratedId);
        return autoGeneratedId;
    }

    @Override
    public void update(UserStatus entity) {
        helper.update(UPDATE_USER_STATUS_SQL, entity.getStatus(), entity.getId());
    }

    @Override
    public boolean delete(Integer id) {
        return helper.delete(DELETE_USER_STATUS_SQL, id);
    }
}