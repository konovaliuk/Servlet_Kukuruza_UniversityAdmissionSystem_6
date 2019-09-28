package ua.company.training.kukuruza.dbConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.persistenceException.ConnectionException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements IConnectionFactory {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionFactoryImpl.class);
    private static final ConnectionFactoryImpl INSTANCE = new ConnectionFactoryImpl();
    private static final DataSource DATA_SOURCE;

    static {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/uas_db");
            DATA_SOURCE = new DataSourceProxy(ds);
        } catch (NamingException e) {
            LOGGER.error("Fail creating instance", e);
            throw new ConnectionException(e);
        }
    }

    private ConnectionFactoryImpl() {

    }

    public static ConnectionFactoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Connection getConnection() {
        try {
            return DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Can't get connection", e);
            throw new ConnectionException(e);
        }
    }
}