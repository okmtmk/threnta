package unit;

import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

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

    @Test
    public void testRead() throws SQLException {
        Connection connection =
                DriverManager.getConnection(
                        "jdbc:derby:/Users/okamoto/Repositories/threnta/database/threnta;",
                        "okmt",
                        "threnta"
                );
        Statement statement = connection.createStatement();

        ResultSet set = statement.executeQuery("select * from OKMT.TALKERS");

        if (set.next()) {
            assertEquals(1, set.getLong("ID"));
        } else {
            fail();
        }

        set.close();
        statement.close();
        connection.close();
    }
}
