package unit.queries;

import builder.models.room.RoomQuery;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertTrue;

public class RoomQueryTest {
    @Test
    public void testGet() throws SQLException {
        RoomQuery select = new RoomQuery();

        String driver = "jdbc:derby:/Users/okamoto/Repositories/threnta/database/threnta;";
        Connection connection
                = DriverManager.getConnection(driver, "okmt", "threnta");
        Statement statement = connection.createStatement();

        ResultSet set = select.get(statement);
        assertTrue(set.next());

        statement.close();
        connection.close();
    }

    @Test
    public void testScopeId() throws SQLException {
        RoomQuery select = new RoomQuery();

        String driver = "jdbc:derby:/Users/okamoto/Repositories/threnta/database/threnta;";
        Connection connection
                = DriverManager.getConnection(driver, "okmt", "threnta");
        Statement statement = connection.createStatement();

        ResultSet set = select.scopeId(1).get(statement);
        assertTrue(set.next());

        statement.close();
        connection.close();
    }

    @Test
    public void testCreateTalkerId() throws SQLException {
        RoomQuery select = new RoomQuery();

        String driver = "jdbc:derby:/Users/okamoto/Repositories/threnta/database/threnta;";
        Connection connection
                = DriverManager.getConnection(driver, "okmt", "threnta");
        Statement statement = connection.createStatement();

        ResultSet set = select.scopeCreateTalkerId(1).get(statement);
        assertTrue(set.next());

        statement.close();
        connection.close();
    }

    @Test
    public void testScope() throws SQLException {
        RoomQuery select = new RoomQuery();

        String driver = "jdbc:derby:/Users/okamoto/Repositories/threnta/database/threnta;";
        Connection connection
                = DriverManager.getConnection(driver, "okmt", "threnta");
        Statement statement = connection.createStatement();

        ResultSet set = select.scopeCreateTalkerId(1).scopeId(1).get(statement);
        assertTrue(set.next());

        statement.close();
        connection.close();
    }

    @Test
    public void testLimit() throws SQLException {
        RoomQuery select = new RoomQuery();

        String driver = "jdbc:derby:/Users/okamoto/Repositories/threnta/database/threnta;";
        Connection connection
                = DriverManager.getConnection(driver, "okmt", "threnta");
        Statement statement = connection.createStatement();

        ResultSet set = select.limit(1).get(statement);
        assertTrue(set.next());

        statement.close();
        connection.close();
    }

    @Test
    public void testAll() throws SQLException {
        RoomQuery select = new RoomQuery();

        String driver = "jdbc:derby:/Users/okamoto/Repositories/threnta/database/threnta;";
        Connection connection
                = DriverManager.getConnection(driver, "okmt", "threnta");
        Statement statement = connection.createStatement();

        ResultSet set = select.scopeCreateTalkerId(1).scopeId(1).limit(1).get(statement);
        assertTrue(set.next());

        statement.close();
        connection.close();
    }
}
