package ua.company.training.kukuruza.persistence.dao.daoMySql;

import ua.company.training.kukuruza.persistence.dbConnection.ConnectionFactoryImpl;
import ua.company.training.kukuruza.persistence.dbConnection.IConnectionFactory;
import ua.company.training.kukuruza.persistence.exception.PersistenceException;

import java.sql.*;
import java.util.*;
import java.util.function.Function;

/**
 * This helper class was created to avoid a lot of JDBC boilerplate code.
 *
 * @author Andrii Kukuruza
 */
public class MySqlDaoHelper {
    private static final MySqlDaoHelper INSTANCE = new MySqlDaoHelper(ConnectionFactoryImpl.getInstance());
    private IConnectionFactory factory;

    private MySqlDaoHelper(IConnectionFactory factory) {
        this.factory = factory;
    }

    public static MySqlDaoHelper getInstance() {
        return INSTANCE;
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
                throw new PersistenceException(e);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    public <T> List<T> getList(String sql, Function<ResultSet, T> mapper) {
        try (Connection c = factory.getConnection();
             Statement s = c.createStatement()) {
            try (ResultSet rs = s.executeQuery(sql)) {
                return getEntitiesList(mapper, rs);
            } catch (SQLException e) {
                throw new PersistenceException(e);
            }
        } catch (SQLException e) {
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
                throw new PersistenceException(e);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    public boolean delete(String sql, Object id) {
        try (Connection c = factory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            setPrepareStatementParameters(ps, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    public void update(String sql, Object... values) {
        try (Connection c = factory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            setPrepareStatementParameters(ps, values);
            ps.executeUpdate();
        } catch (SQLException e) {
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
                    throw new PersistenceException("There is no generated key");
                }
            } catch (SQLException e) {
                throw new PersistenceException(e);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    public Long getRowsCount(String sql) {
        try (Connection c = factory.getConnection();
             Statement s = c.createStatement()) {
            try (ResultSet rs = s.executeQuery(sql)) {
                if (rs.next()) {
                    return rs.getLong(1);
                } else {
                    throw new PersistenceException();
                }
            } catch (SQLException ex) {
                throw new PersistenceException(ex);
            }
        } catch (SQLException e) {
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
