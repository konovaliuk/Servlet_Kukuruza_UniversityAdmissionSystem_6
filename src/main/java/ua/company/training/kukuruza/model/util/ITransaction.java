package ua.company.training.kukuruza.model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.model.exception.TransactionException;
import ua.company.training.kukuruza.model.connection.ConnectionFactoryImpl;
import ua.company.training.kukuruza.model.exception.PersistenceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@FunctionalInterface
public interface ITransaction {
    Logger LOGGER = LogManager.getLogger(ITransaction.class);

    void transactionBody();

    static boolean doTransaction(ITransaction t) {
        return doTransaction(t, Connection.TRANSACTION_READ_COMMITTED);
    }

    static boolean doTransaction(ITransaction t, int transactionIsolationLevel) {
        ConnectionFactoryImpl factory = ConnectionFactoryImpl.getInstance();
        Connection connection = factory.getConnection();
        if (Objects.nonNull(connection)) {
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
                    LOGGER.error("Transaction preparing error", prepareException);
                    throw new TransactionException(prepareException);
                }
                try {
                    t.transactionBody();
                } catch (PersistenceException persistenceException) {
                    LOGGER.info("Transaction body fail. Trying rollback", persistenceException);
                    try {
                        connection.rollback();
                        isTransactionDoneWithoutRollback = false;
                    } catch (SQLException rollbackException) {
                        rollbackException.addSuppressed(persistenceException);
                        LOGGER.error("Rollback fail", rollbackException);
                        throw new TransactionException(rollbackException);
                    }
                }
                if (isTransactionDoneWithoutRollback) {
                    try {
                        connection.commit();
                    } catch (SQLException commitException) {
                        LOGGER.error("Commit error", commitException);
                        throw new TransactionException(commitException);
                    }
                }
                try {
                    connection.setAutoCommit(autoCommitState);
                    if (connection.getTransactionIsolation() != oldTransactionIsolation)
                        connection.setTransactionIsolation(oldTransactionIsolation);
                } catch (SQLException afterCommitException) {
                    LOGGER.error("Can't change connection state", afterCommitException);
                    throw new TransactionException(afterCommitException);
                }
                return isTransactionDoneWithoutRollback;
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Can't close connection", e);
                }
            }
        } else {
            LOGGER.error("No connection");
            throw new TransactionException("Connection equals null");
        }
    }
}
