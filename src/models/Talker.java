package models;

import exceptions.ModelNotFoundException;

import java.sql.*;

public class Talker extends Model {
    private static final String MODEL_NAME = "Talker";

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

    /**
     * Talkerを取得
     *
     * @param id Talker ID
     * @return IDが一致したTalkerインスタンス
     * @throws SQLException           SQLエラー
     * @throws ModelNotFoundException IDの一致するモデルが存在しなかったとき
     */
    static public Talker find(long id) throws SQLException, ModelNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet set = statement.executeQuery(
                "select ID,SESSION_ID,CREATED_AT,UPDATED_AT " +
                        "from TALKERS where ID = " + id
        );

        if (!set.next()) {
            throw new ModelNotFoundException(MODEL_NAME, id);
        }

        Talker model = new Talker(
                set.getLong(1),
                set.getString(2),
                set.getTimestamp(3),
                set.getTimestamp(4)
        );

        set.close();
        statement.close();
        connection.close();

        return model;
    }
}
