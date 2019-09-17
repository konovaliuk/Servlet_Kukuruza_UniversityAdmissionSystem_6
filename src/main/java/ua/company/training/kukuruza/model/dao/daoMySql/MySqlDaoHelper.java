package ua.company.training.kukuruza.model.dao.daoMySql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.model.connection.ConnectionFactoryImpl;
import ua.company.training.kukuruza.model.connection.IConnectionFactory;
import ua.company.training.kukuruza.model.exception.PersistenceException;

import java.sql.*;
import java.util.*;
import java.util.function.Function;

public class MySqlDaoHelper {
    private static final Logger LOGGER = LogManager.getLogger(MySqlDaoHelper.class);
    private static MySqlDaoHelper instance;
    private IConnectionFactory factory;

    private MySqlDaoHelper(IConnectionFactory factory) {
        this.factory = factory;
    }

    public static MySqlDaoHelper getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (MySqlDaoHelper.class) {
                if (Objects.isNull(instance))
                    instance = new MySqlDaoHelper(ConnectionFactoryImpl.getInstance());
            }
        }
        return instance;
    }

    public <T> Optional<T> get(String sql, Function<ResultSet, T> mapper, Object... parameters) {
        try (Connection c = factory.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            setPrepareStatementParameters(s, parameters);
            try (ResultSet rs = s.executeQuery()) {
                if (rs.next()) {
                    T entity = mapper.apply(rs);
                    return Optional.of(entity);
                } else {
                    return Optional.empty();
                }
            } catch (SQLException e) {
                LOGGER.error("Result set error", e);
                throw new PersistenceException(e);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't start request executing", e);
            throw new PersistenceException(e);
        }
    }

    public <T> List<T> getList(String sql, Function<ResultSet, T> mapper) {
        try (Connection c = factory.getConnection();
             Statement s = c.createStatement()) {
            try (ResultSet rs = s.executeQuery(sql)) {
                return getEntitiesList(mapper, rs);
            } catch (SQLException e) {
                LOGGER.error("Result set error", e);
                throw new PersistenceException(e);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't start request executing", e);
            throw new PersistenceException(e);
        }
    }

    public <T> List<T> getList(String sql, Function<ResultSet, T> mapper, Object... parameters) {
        try (Connection c = factory.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            setPrepareStatementParameters(s, parameters);
            try (ResultSet rs = s.executeQuery()) {
                return getEntitiesList(mapper, rs);
            } catch (SQLException e) {
                LOGGER.error("Result set error", e);
                throw new PersistenceException(e);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't start request executing", e);
            throw new PersistenceException(e);
        }
    }

    public boolean delete(String sql, Object id) {
        try (Connection c = factory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            setPrepareStatementParameters(ps, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Can't delete entity", e);
            throw new PersistenceException(e);
        }
    }

    public void update(String sql, Object... values) {
        try (Connection c = factory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            setPrepareStatementParameters(ps, values);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can't update entity", e);
            throw new PersistenceException(e);
        }
    }

    public Integer save(String sql, Object... values) {
        return save(sql, 1, Integer.class, values);
    }

    public <PK> PK save(String sql, Class<PK> pkType, Object... values) {
        return save(sql, 1, pkType, values);
    }

    public <PK> PK save(String sql, int pkColumnIndex, Class<PK> pkType, Object... values) {
        try (Connection c = factory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setPrepareStatementParameters(ps, values);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return getGeneratedKey(rs, pkType, pkColumnIndex);
                } else {
                    LOGGER.error("There is no generated key");
                    throw new PersistenceException("There is no generated key");
                }
            } catch (SQLException e) {
                LOGGER.error("Can't get generated key", e);
                throw new PersistenceException(e);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't insert entity", e);
            throw new PersistenceException(e);
        }
    }

    public void saveWithoutAutoKey(String sql, Object... values) {
        try (Connection c = factory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            setPrepareStatementParameters(ps, values);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can't insert entity", e);
            throw new PersistenceException(e);
        }
    }

    public String buildSql(StringBuilder template, Collection<?> values) {
        values.forEach((value) -> template.append(value).append(","));
        template.setLength(template.length() - 1);
        template.append(")");
        return template.toString();
    }

    private void setPrepareStatementParameters(PreparedStatement ps, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            ps.setObject(i + 1, values[i]);
        }
    }

    private <PK> PK getGeneratedKey(ResultSet rs, Class<PK> pkType, int pkColumnIndex) throws SQLException {
        if (Objects.isNull(pkType))
            throw new PersistenceException("Primary key type is null");
        if (pkType.isAssignableFrom(Integer.class)) {
            Integer key = rs.getInt(pkColumnIndex);
            return pkType.cast(key);
        } else if (pkType.isAssignableFrom(Long.class)) {
            Long key = rs.getLong(pkColumnIndex);
            return pkType.cast(key);
        } else {
            LOGGER.error("Unsupported key type");
            throw new PersistenceException("Unsupported key type");
        }
    }

    private <T> List<T> getEntitiesList(Function<ResultSet, T> mapper, ResultSet rs) throws SQLException {
        List<T> entities = new ArrayList<>();
        while (rs.next()) {
            T entity = mapper.apply(rs);
            entities.add(entity);
        }
        return entities;
    }
}
