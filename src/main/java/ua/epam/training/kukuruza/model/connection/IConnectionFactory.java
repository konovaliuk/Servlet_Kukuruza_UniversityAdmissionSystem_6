package ua.epam.training.kukuruza.model.connection;

import java.sql.Connection;

public interface IConnectionFactory {
    Connection getConnection();
}
