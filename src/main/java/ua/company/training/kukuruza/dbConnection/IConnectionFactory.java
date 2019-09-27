package ua.company.training.kukuruza.dbConnection;

import java.sql.Connection;

public interface IConnectionFactory {
    Connection getConnection();
}
