package ua.company.training.kukuruza.dbConnection;

import java.sql.Connection;

/**
 * The interface was created for getting database connection.
 *
 * @author Andrii Kukuruza
 */
public interface IConnectionFactory {
    Connection getConnection();
}
