package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class Model {
    protected static Connection getConnection() throws SQLException {
        String driver = "jdbc:derby:/Users/okamoto/Repositories/threnta/database/threnta;";
        return DriverManager.getConnection(driver, "okmt", "threnta");
    }
}
