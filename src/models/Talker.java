package models;

import java.sql.*;

public class Talker extends Model {
    private long id;
    private String sessionId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Talker(long id, String sessionId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /*
    Properties
     */

    public long getId() {
        return id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /*
    Static methods
     */

    static public Talker find(long id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet set = statement.executeQuery(
                "select ID,SESSION_ID,CREATED_AT,UPDATED_AT " +
                        "from TALKERS where ID = " + id
        );

        set.next();
        Talker model = new Talker(
                set.getLong(1),
                set.getString(2),
                set.getTimestamp(3),
                set.getTimestamp(4)
        );

        statement.close();
        connection.close();

        return model;
    }
}
