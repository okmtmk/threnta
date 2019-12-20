package models;

import java.sql.*;

public class Message extends Model {
    static final String MODEL_NAME = "MESSAGES";

    static final String TALKER_ID = "TALKER_ID";
    static final String ROOM_ID = "ROOM_ID";
    static final String MESSAGE = "MESSAGE";

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
    Properties
     */

    public long getTalkerId() {
        return talkerId;
    }

    public long getRoomID() {
        return roomID;
    }

    public String getMessage() {
        return message;
    }

    public Talker getTalker() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet set = statement.executeQuery(
                "select * from " + Talker.MODEL_NAME + " where " + Talker.ID + " = " + talkerId
        );

        if (!set.next()) {
            statement.close();
            connection.close();

            throw new SQLException();
        }

        Talker model = Talker.makeInstance(set);

        statement.close();
        connection.close();

        return model;
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

        Message model = makeInstance(set);

        statement.close();
        connection.close();

        return model;
    }

    /**
     * ResultSetからMessageクラスのインスタンスを生成
     *
     * @param set Messagesエンティティを検索した結果
     * @return Messageインスタンス
     * @throws SQLException SQLエラー
     */
    static Message makeInstance(ResultSet set) throws SQLException {
        return new Message(
                set.getLong(ID),
                set.getTimestamp(CREATED_AT),
                set.getTimestamp(UPDATED_AT),
                set.getLong(TALKER_ID),
                set.getLong(ROOM_ID),
                set.getString(MESSAGE)
        );
    }
}
