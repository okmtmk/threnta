package models;

import exceptions.ModelNotFoundException;

import java.sql.*;
import java.util.Calendar;

public class Talker extends Model {
    private static final String MODEL_NAME = "TALKERS";

    protected String sessionId;

    public Talker(long id, String sessionId, Timestamp createdAt, Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.sessionId = sessionId;
    }

    /*
    Properties
     */

    public String getSessionId() {
        return sessionId;
    }

    /*
    Instance methods
     */



    /*
    Static methods
     */

    /**
     * @param sessionId セッションID
     * @return 作成されたTalker
     * @throws SQLException SQLエラー
     */
    static public Talker create(String sessionId) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        Timestamp now = new Timestamp(Calendar.getInstance().getTime().getTime());

        //language=sql
        String sql = "insert into " + MODEL_NAME + "(session_id, created_at, updated_at) " +
                "values (" +
                "'" + sessionId + "', " +
                "'" + now + "', " +
                "'" + now + "'" +
                ")";

        if (statement.executeLargeUpdate(sql) != 1) {
            statement.close();
            connection.close();

            throw new SQLException();
        }

        long id = getLastInsertedId(MODEL_NAME, statement);

        statement.close();
        connection.close();

        try {

            return find(id);
        } catch (Exception e) {
            e.printStackTrace();

            // もし、レコードが見つからない場合はSQL Exceptionを送出
            throw new SQLException();
        }
    }

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
                        "from " + MODEL_NAME + " where ID = " + id
        );

        if (!set.next()) {
            set.close();
            statement.close();
            connection.close();

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
