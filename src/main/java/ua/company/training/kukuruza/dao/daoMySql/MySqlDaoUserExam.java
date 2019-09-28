package ua.company.training.kukuruza.dao.daoMySql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.dao.IDaoUserExam;
import ua.company.training.kukuruza.dao.mapper.UserExamMapper;
import ua.company.training.kukuruza.entity.UserExam;
import ua.company.training.kukuruza.dbConnection.IConnectionFactory;
import ua.company.training.kukuruza.persistenceException.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MySqlDaoUserExam implements IDaoUserExam {
    private static final Logger LOGGER = LogManager.getLogger(MySqlDaoUserExam.class);
    private static final String GET_USER_EXAM_BY_ID_SQL = "SELECT * FROM user_exam WHERE id = ?";
    private static final String GET_USER_EXAM_BY_USER_ID_SQL = "SELECT * FROM user_exam WHERE user_id = ?";
    private static final String GET_ALL_USER_EXAM_SQL = "SELECT * FROM user_exam";
    private static final String INSERT_USER_EXAM_SQL = "INSERT INTO user_exam VALUES (NULL, ?, ?)";
    private static final String UPDATE_USER_EXAM_SQL = "UPDATE user_exam SET " +
            "user_id = ?, exam_id = ? WHERE id = ?";
    private static final String DELETE_USER_EXAM_SQL = "DELETE FROM user_exam WHERE id = ?";
    private static final String DELETE_USER_EXAM_BY_USER_ID_AND_EXAM_ID_SQL = "DELETE FROM user_exam " +
            "WHERE user_id = ? AND exam_id = ?";
    private MySqlDaoHelper helper = MySqlDaoHelper.getInstance();
    private IConnectionFactory factory;

    public MySqlDaoUserExam(IConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<UserExam> find(Long id) {
        return helper.get(GET_USER_EXAM_BY_ID_SQL, UserExamMapper::map, id);
    }

    @Override
    public List<UserExam> findAll() {
        return helper.getList(GET_ALL_USER_EXAM_SQL, UserExamMapper::map);
    }

    @Override
    public Set<Integer> findExamsIdByUserId(Long userId) {
        List<UserExam> userExams = helper.getList(GET_USER_EXAM_BY_USER_ID_SQL, UserExamMapper::map, userId);
        return userExams.stream()
                .map(UserExam::getExamId)
                .collect(Collectors.toSet());
    }

    @Override
    public Long save(UserExam entity) {
        Long autoGeneratedId = helper.save(INSERT_USER_EXAM_SQL, Long.class, entity.getUserId(), entity.getExamId());
        entity.setId(autoGeneratedId);
        return autoGeneratedId;
    }

    @Override
    public void save(List<UserExam> userExams) {
        executeSql(INSERT_USER_EXAM_SQL, userExams);
    }

    @Override
    public void update(UserExam entity) {
        helper.update(UPDATE_USER_EXAM_SQL, entity.getUserId(), entity.getExamId(), entity.getId());
    }

    @Override
    public boolean delete(Long id) {
        return helper.delete(DELETE_USER_EXAM_SQL, id);
    }

    @Override
    public void delete(List<UserExam> userExams) {
        executeSql(DELETE_USER_EXAM_BY_USER_ID_AND_EXAM_ID_SQL, userExams);
    }

    private void executeSql(String sql, List<UserExam> userExams) {
        if (userExams.isEmpty())
            return;
        try (Connection c = factory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            for (UserExam userExam : userExams) {
                ps.setLong(1, userExam.getUserId());
                ps.setInt(2, userExam.getExamId());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            LOGGER.error("Can't execute", e);
            throw new PersistenceException(e);
        }
    }
}