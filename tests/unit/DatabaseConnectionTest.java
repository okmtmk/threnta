package unit;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

public class DatabaseConnectionTest {
    @Test
    public void testConnection() throws SQLException, ClassNotFoundException {
//        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String driver = "jdbc:derby:/Users/okamoto/Repositories/threnta/database/threnta;";
        Connection connection
                = DriverManager.getConnection(driver, "okmt", "threnta");

        assertFalse(connection.isClosed());
        connection.close();
    }
}
