package models;

import exceptions.ModelNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Room extends Model {
    static final String MODEL_NAME = "ROOMS";

    static final String CREATE_TALKER_ID = "CREATE_TALKER_ID";
    static final String NAME = "NAME";
    static final String DESCRIPTION = "DESCRIPTION";

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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 関連するメッセージを全件取得
     *
     * @return メッセージ
     * @throws SQLException SQLエラー
     */
    public List<Message> getMessages() throws SQLException {
        return getMessages(-1);
    }

    /**
     * 関連するメッセージを指定件数取得
     *
     * @param limit 取得する数
     * @return メッセージ
     * @throws SQLException SQLエラー
     */
    public List<Message> getMessages(int limit) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        String sqlLimit = limit > 0 ? " fetch first " + limit + " rows only " : "";
        ResultSet set = statement.executeQuery(
                "select * from " + Message.MODEL_NAME +
                        " where " + Message.ROOM_ID + " = " + id +
                        sqlLimit
        );

        List<Message> messages = new ArrayList<>();
        while (set.next()) {
            messages.add(
                    Message.makeInstance(set)
            );
        }

        statement.close();
        connection.close();

        return messages;
    }

    /*
    Instance methods
     */

    /**
     * レコードの更新
     *
     * @return 同一のインスタンス
     * @throws SQLException SQLエラー
     */
    public Room update() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        if (statement.executeUpdate(
                "update " + MODEL_NAME +
                        " set " + NAME + " = '" + name + "', " +
                        DESCRIPTION + " = '" + description + "', " +
                        UPDATED_AT + " = '" + now() + "'" +
                        "where " + ID + " = " + id
        ) != 1) {
            throw new SQLException();
        }

        return this;
    }

    /*
    Static methods
     */

    public static Room create(long createTalkerId, String name, String description) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        if (statement.executeUpdate(
                "insert into " + MODEL_NAME +
                        "(" +
                        NAME + ", " +
                        DESCRIPTION + ", " +
                        CREATE_TALKER_ID + ", " +
                        CREATED_AT + ", " +
                        UPDATED_AT +
                        ") " +
                        "VALUES (" +
                        "'" + name + "', " +
                        "'" + description + "', " +
                        createTalkerId + ", " +
                        "'" + now() + "', " +
                        "'" + now() + "'" +
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
