package ua.company.training.kukuruza.persistence.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.persistence.exception.TransactionException;
import ua.company.training.kukuruza.persistence.dbConnection.ConnectionFactoryImpl;
import ua.company.training.kukuruza.persistence.exception.PersistenceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * The interface was created for transaction support.
 * It's important to understand that it works in conjunction
 * with a datasource proxy and a connection proxy
 * which are inside dbConnection package.
 *
 * @see ua.company.training.kukuruza.persistence.dbConnection.DataSourceProxy
 * @see ua.company.training.kukuruza.persistence.dbConnection.ConnectionProxy
 * @author Andrii Kukuruza
 */
@FunctionalInterface
public interface ITransaction {
    Logger LOGGER = LogManager.getLogger(ITransaction.class);

    void transactionBody();

    static boolean doTransaction(ITransaction t) {
        return doTransaction(t, Connection.TRANSACTION_READ_COMMITTED);
    }

    static boolean doTransaction(ITransaction t, int transactionIsolationLevel) {
        LOGGER.info("Transaction begin");
        ConnectionFactoryImpl factory = ConnectionFactoryImpl.getInstance();
        Connection connection = factory.getConnection();
        if (Objects.nonNull(connection)) {
            LOGGER.info("Got connection");
            try {
                boolean isTransactionDoneWithoutRollback = true;
                boolean autoCommitState;
                int oldTransactionIsolation;
                try {
                    autoCommitState = connection.getAutoCommit();
                    connection.setAutoCommit(false);
                    oldTransactionIsolation = connection.getTransactionIsolation();
                    if (oldTransactionIsolation != transactionIsolationLevel)
                        connection.setTransactionIsolation(transactionIsolationLevel);
                } catch (SQLException prepareException) {
                    throw new TransactionException(prepareException);
                }
                LOGGER.info("Changed connection autoCommit state to false");
                try {
                    t.transactionBody();
                    LOGGER.info("Transaction body was successfully done");
                } catch (PersistenceException persistenceException) {
                    LOGGER.info("Transaction body fail. Trying rollback", persistenceException);
                    try {
                        connection.rollback();
                        isTransactionDoneWithoutRollback = false;
                        LOGGER.info("Rollback successfully done");
                    } catch (SQLException rollbackException) {
                        rollbackException.addSuppressed(persistenceException);
                        throw new TransactionException(rollbackException);
                    }
                }
                if (isTransactionDoneWithoutRollback) {
                    try {
                        connection.commit();
                        LOGGER.info("Commit successfully done");
                    } catch (SQLException commitException) {
                        throw new TransactionException(commitException);
                    }
                }
                try {
                    connection.setAutoCommit(autoCommitState);
                    if (connection.getTransactionIsolation() != oldTransactionIsolation)
                        connection.setTransactionIsolation(oldTransactionIsolation);
                    LOGGER.info("Changed connection state to default");
                } catch (SQLException afterCommitException) {
                    throw new TransactionException(afterCommitException);
                }
                return isTransactionDoneWithoutRollback;
            } finally {
                try {
                    connection.close();
                    LOGGER.info("Transaction end");
                } catch (SQLException e) {
                    LOGGER.error("Can't close connection", e);
                }
            }
        } else {
            throw new TransactionException("Connection equals null");
        }
    }
}
