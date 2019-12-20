package models;

import exceptions.ModelNotFoundException;

import java.sql.*;

public class Room extends Model {
    protected static final String MODEL_NAME = "ROOMS";

    protected static final String CREATE_TALKER_ID = "CREATE_TALKER_ID";
    protected static final String NAME = "NAME";
    protected static final String DESCRIPTION = "DESCRIPTION";

    protected long createTalkerId;
    protected String name;
    protected String description;

    public Room(
            long id, Timestamp createdAt, Timestamp updatedAt, long createTalkerId, String name, String description
    ) {
        super(id, createdAt, updatedAt);
        this.createTalkerId = createTalkerId;
        this.name = name;
        this.description = description;
    }

    /*
    Properties
     */

    public long getCreateTalkerId() {
        return createTalkerId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /*
    Static methods
     */

    public static Room create(long createTalkerId, String name, String description) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        if (statement.executeUpdate(
                "insert into " + MODEL_NAME + "(" + NAME + ", " + DESCRIPTION + ", " + CREATE_TALKER_ID + ") " +
                        "VALUES (" +
                        "'" + name + "', " +
                        "'" + description + "', " +
                        createTalkerId +
                        ")"
        ) != 1) {
            statement.close();
            connection.close();

            throw new SQLException();
        }

        try {
            long id = getLastInsertedId(MODEL_NAME, statement);
            return find(id);
        } catch (Exception e) {
            throw new SQLException();
        }
    }

    public static Room find(long id) throws SQLException, ModelNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet set = statement.executeQuery(
                "select * from " + MODEL_NAME + " where " + ID + " = " + id
        );

        if (!set.next()) {
            throw new ModelNotFoundException(MODEL_NAME, id);
        }

        Room model = new Room(
                set.getLong(ID),
                set.getTimestamp(CREATED_AT),
                set.getTimestamp(UPDATED_AT),
                set.getLong(CREATE_TALKER_ID),
                set.getString(NAME),
                set.getString(DESCRIPTION)
        );

        statement.close();
        connection.close();

        return model;
    }
}
