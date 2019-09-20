package ua.company.training.kukuruza.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.model.exception.ConnectionException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements IConnectionFactory {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionFactoryImpl.class);
    private static final ConnectionFactoryImpl INSTANCE;
    private DataSource dataSource;

    static {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/uas_db");
            DataSourceProxy dsProxy = new DataSourceProxy(ds);
            INSTANCE = new ConnectionFactoryImpl(dsProxy);
        } catch (NamingException e) {
            LOGGER.error("Fail creating instance", e);
            throw new ConnectionException(e);
        }
    }

    private ConnectionFactoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ConnectionFactoryImpl getInstance() {
        return INSTANCE;
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
