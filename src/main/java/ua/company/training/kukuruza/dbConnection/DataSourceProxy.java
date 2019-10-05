package ua.company.training.kukuruza.dbConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Objects;

/**
 * The class is a {@link javax.sql.DataSource } wrapper.
 * It allows to share a connection to different dao methods
 * inside transaction body.
 *
 * @see ConnectionProxy
 * @see ua.company.training.kukuruza.transaction.ITransaction
 * @author Andrii Kukuruza
 */
public class DataSourceProxy implements DataSource {
    private final static Logger LOGGER = LogManager.getLogger(DataSourceProxy.class);
    private DataSource dataSource;
    private ThreadLocal<ConnectionProxy> connectionProxyThreadLocal = new ThreadLocal<>();

    DataSourceProxy(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    void removeConnection() {
        ConnectionProxy connectionProxy = connectionProxyThreadLocal.get();
        if (Objects.nonNull(connectionProxy)) {
            connectionProxyThreadLocal.remove();
            LOGGER.debug("ConnectionProxy was successfully removed");
        } else {
            LOGGER.warn("ConnectionProxyThreadLocal is empty, can't remove ConnectionProxy");
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        ConnectionProxy connectionProxy = connectionProxyThreadLocal.get();
        if (Objects.isNull(connectionProxy)) {
            LOGGER.debug("ConnectionProxy is null");
            Connection connection = dataSource.getConnection();
            connectionProxy = new ConnectionProxy(connection, this);
            connectionProxyThreadLocal.set(connectionProxy);
            LOGGER.debug("ConnectionProxy was successfully created");
        } else {
            LOGGER.debug("ConnectionProxy was gotten from connectionProxyThreadLocal");
        }
        connectionProxy.incrementCloseCounter();
        return connectionProxy;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return dataSource.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        dataSource.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        dataSource.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return dataSource.getLoginTimeout();
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return dataSource.getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return dataSource.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return dataSource.isWrapperFor(iface);
    }
}
