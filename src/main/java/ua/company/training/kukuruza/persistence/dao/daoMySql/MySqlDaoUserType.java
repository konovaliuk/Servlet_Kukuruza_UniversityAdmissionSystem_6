package ua.company.training.kukuruza.persistence.dao.daoMySql;

import ua.company.training.kukuruza.persistence.dbConnection.IConnectionFactory;
import ua.company.training.kukuruza.persistence.dao.IDaoUserType;
import ua.company.training.kukuruza.persistence.entity.UserType;
import ua.company.training.kukuruza.persistence.dao.mapper.UserTypeMapper;

import java.util.List;
import java.util.Optional;

public class MySqlDaoUserType implements IDaoUserType {
    private static final String GET_USER_TYPE_BY_ID_SQL = "SELECT * FROM user_type WHERE id = ?";
    private static final String GET_ALL_USER_TYPES_SQL = "SELECT * FROM user_type";
    private static final String INSERT_USER_TYPE_SQL = "INSERT INTO user_type VALUES (NULL, ?)";
    private static final String UPDATE_USER_TYPE_SQL = "UPDATE user_type SET user_role = ? WHERE id = ?";
    private static final String DELETE_USER_TYPE_SQL = "DELETE FROM user_type WHERE id = ?";
    private MySqlDaoHelper helper = MySqlDaoHelper.getInstance();
    private IConnectionFactory factory;

    MySqlDaoUserType(IConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<UserType> find(Integer id) {
        return helper.get(GET_USER_TYPE_BY_ID_SQL, UserTypeMapper::map, id);
    }

    @Override
    public List<UserType> findAll() {
        return helper.getList(GET_ALL_USER_TYPES_SQL, UserTypeMapper::map);
    }

    @Override
    public Integer save(UserType entity) {
        Integer autoGeneratedId = helper.save(INSERT_USER_TYPE_SQL, entity.getUserRole());
        entity.setId(autoGeneratedId);
        return autoGeneratedId;
    }

    @Override
    public void update(UserType entity) {
        helper.update(UPDATE_USER_TYPE_SQL, entity.getUserRole(), entity.getId());
    }

    @Override
    public boolean delete(Integer id) {
        return helper.delete(DELETE_USER_TYPE_SQL, id);
    }
}
