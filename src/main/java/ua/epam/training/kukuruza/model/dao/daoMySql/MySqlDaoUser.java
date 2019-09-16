package ua.epam.training.kukuruza.model.dao.daoMySql;

import ua.epam.training.kukuruza.model.connection.IConnectionFactory;
import ua.epam.training.kukuruza.model.dao.IDaoUser;
import ua.epam.training.kukuruza.model.dao.mapper.UserMapper;
import ua.epam.training.kukuruza.model.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MySqlDaoUser implements IDaoUser {
    private static final String GET_USER_BY_ID_SQL = "SELECT * FROM user WHERE id = ?";
    private static final String GET_USER_BY_EMAIL_SQL = "SELECT * FROM user WHERE email = ?";
    private static final String GET_USER_BY_FIRST_NAME_AND_SECOND_NAME_SQL =
            "SELECT * FROM user WHERE first_name = ? AND second_name = ?";
    private static final String GET_USER_BY_LOGIN_SQL = "SELECT * FROM user WHERE login = ?";
    private static final String GET_USER_BY_PASSPORT_CODE_SQL = "SELECT * FROM user WHERE passport_code = ?";
    private static final String GET_ALL_USERS_SQL = "SELECT * FROM user";
    private static final String INSERT_USER_SQL = "INSERT INTO user VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_SQL = "UPDATE user SET " +
            "login = ?, password = ?, first_name = ?, second_name = ?, gender = ?, " +
            "email = ?, phone = ?, passport_code = ?, user_type_id = ?, user_status_id = ? " +
            "WHERE id = ?";
    private static final String DELETE_USER_SQL = "DELETE FROM user WHERE id = ?";
    private MySqlDaoHelper helper = MySqlDaoHelper.getInstance();
    private IConnectionFactory factory;

    public MySqlDaoUser(IConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<User> get(Long id) {
        return helper.get(GET_USER_BY_ID_SQL, UserMapper::map, id);
    }

    @Override
    public List<User> getAll() {
        return helper.getList(GET_ALL_USERS_SQL, UserMapper::map);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return helper.get(GET_USER_BY_EMAIL_SQL, UserMapper::map, email);
    }

    @Override
    public List<User> getByFirstNameAndSecondName(String firstName, String secondName) {
        return helper.getList(GET_USER_BY_FIRST_NAME_AND_SECOND_NAME_SQL, UserMapper::map, firstName, secondName);
    }

    @Override
    public List<User> getByIdSet(Set<Long> usersId) {
        if (usersId.isEmpty())
            return Collections.emptyList();
        String sql = helper.buildSql(new StringBuilder("SELECT * FROM user WHERE id IN("), usersId);
        return helper.getList(sql, UserMapper::map);
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return helper.get(GET_USER_BY_LOGIN_SQL, UserMapper::map, login);
    }

    @Override
    public Optional<User> getByPassportCode(String passportCode) {
        return helper.get(GET_USER_BY_PASSPORT_CODE_SQL, UserMapper::map, passportCode);
    }

    @Override
    public Long save(User entity) {
        Long autoGeneratedId = helper.save(INSERT_USER_SQL, Long.class,
                entity.getLogin(),
                entity.getPassword(),
                entity.getFirstName(),
                entity.getSecondName(),
                entity.getGender(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getPassportCode(),
                entity.getUserTypeId(),
                entity.getUserStatusId());
        entity.setId(autoGeneratedId);
        return autoGeneratedId;
    }

    @Override
    public void update(User entity) {
        helper.update(UPDATE_USER_SQL,
                entity.getLogin(),
                entity.getPassword(),
                entity.getFirstName(),
                entity.getSecondName(),
                entity.getGender(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getPassportCode(),
                entity.getUserTypeId(),
                entity.getUserStatusId(),
                entity.getId());
    }

    @Override
    public boolean delete(Long id) {
        return helper.delete(DELETE_USER_SQL, id);
    }
}
