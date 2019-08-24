package ua.epam.training.kukuruza.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.epam.training.kukuruza.model.exception.ConnectionException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionFactoryImpl implements IConnectionFactory {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionFactoryImpl.class);
    private static ConnectionFactoryImpl instance;
    private DataSource dataSource;

    private ConnectionFactoryImpl(DataSource dataSource) {
        LOGGER.debug("Constructing instance");
        this.dataSource = dataSource;
    }

    public static ConnectionFactoryImpl getInstance() {
        if (Objects.isNull(instance)) {
            LOGGER.debug("Begin of creating instance");
            instance = createInstance();
            LOGGER.debug("Successful creating instance");
        }
        return instance;
    }

    private static synchronized ConnectionFactoryImpl createInstance() {
        if (Objects.nonNull(instance)) {
            LOGGER.debug("Another thread already created instance");
            return instance;
        }
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/uas_db");
            return new ConnectionFactoryImpl(ds);
        } catch (NamingException e) {
            LOGGER.debug("Fail creating instance", e);
            throw new ConnectionException(e);
        }
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Can't get connection", e);
            throw new ConnectionException(e);
        }
    }
}
