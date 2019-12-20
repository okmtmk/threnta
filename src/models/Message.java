package models;

import java.sql.*;

public class Message extends Model {
    protected static final String MODEL_NAME = "MESSAGES";
    protected static final String TALKER_ID = "TALKER_ID";
    protected static final String ROOM_ID = "ROOM_ID";
    protected static final String MESSAGE = "MESSAGE";

    protected long talkerId;
    protected long roomID;
    protected String message;

    public Message(
            long id, Timestamp createdAt, Timestamp updatedAt, long talkerId, long roomID, String message
    ) {
        super(id, createdAt, updatedAt);
        this.talkerId = talkerId;
        this.roomID = roomID;
        this.message = message;
    }

    /*
    Static methods
     */

    /**
     * 取得
     *
     * @param id Message ID
     * @return Message
     * @throws SQLException SQLエラー
     */
    public static Message find(long id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet set = statement.executeQuery(
                "select * from " + MODEL_NAME + " where " + ID + " = " + id
        );

        if (!set.next()) {
            throw new SQLException();
        }

        Message model = new Message(
                set.getLong(ID),
                set.getTimestamp(CREATED_AT),
                set.getTimestamp(UPDATED_AT),
                set.getLong(TALKER_ID),
                set.getLong(ROOM_ID),
                set.getString(MESSAGE)
        );

        statement.close();
        connection.close();

        return model;
    }
}
