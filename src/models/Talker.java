package models;

import exceptions.ModelNotFoundException;
import exceptions.SessionIdAlreadyRegisteredException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Talker extends Model {
    public static final String MODEL_NAME = "TALKERS";
    public static final String SESSION_ID = "SESSION_ID";

    protected String sessionId;

    public Talker(long id, Timestamp createdAt, Timestamp updatedAt, String sessionId) {
        super(id, createdAt, updatedAt);
        this.sessionId = sessionId;
    }

    /*
    Properties
     */

    public String getSessionId() {
        return sessionId;
    }

    /**
     * Talkerが作成したルームを取得
     *
     * @return Talkerが作成したルーム
     * @throws SQLException SQLエラー
     */
    public List<Room> getCreatedRooms() throws SQLException {
        return getCreatedRooms(-1);
    }

    /**
     * Talkerが作成したルームを取得
     *
     * @param limit 取得件数
     * @return Talkerが作成したルーム
     * @throws SQLException SQLエラー
     */
    public List<Room> getCreatedRooms(long limit) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        String sqlLimit = limit > 0 ? " fetch first " + limit + " rows only " : "";

        ResultSet set = statement.executeQuery(
                "select * from " + Room.MODEL_NAME + " where " + Room.CREATE_TALKER_ID + " = " + id +
                        sqlLimit
        );

        List<Room> rooms = new ArrayList<>();
        while (set.next()) {
            rooms.add(Room.makeInstance(set));
        }

        statement.close();
        connection.close();

        return rooms;
    }

    /**
     * 自分の発言があるルームを取得
     *
     * @return 自分の発言があるルーム
     * @throws SQLException SQLエラ−
     */
    public List<Room> getTalkedRooms() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet set = statement.executeQuery(
                "select * from " + Room.MODEL_NAME +
                        " where " + Room.ID + " = (" +
                        "select " + Message.ROOM_ID + " from " + Message.MODEL_NAME +
                        " where " + Message.TALKER_ID + " = " + id +
                        ")"
        );

        List<Room> rooms = new ArrayList<>();
        while (set.next()) {
            rooms.add(Room.makeInstance(set));
        }

        statement.close();
        connection.close();

        return rooms;
    }

    /*
    Instance methods
     */



    /*
    Static methods
     */

    /**
     * Talkerを作成
     *
     * @param sessionId セッションID
     * @return 作成されたTalker
     * @throws SQLException                        SQLエラー
     * @throws SessionIdAlreadyRegisteredException セッションIDが登録済み
     */
    public static Talker create(String sessionId) throws SQLException, SessionIdAlreadyRegisteredException {
        if (isSessionIdRegistered(sessionId)) {
            throw new SessionIdAlreadyRegisteredException(sessionId);
        }

        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        //language=sql
        String sql = "insert into " + MODEL_NAME + "(" + SESSION_ID + ", " + CREATED_AT + ", " + UPDATED_AT + ") " +
                "values (" +
                "'" + sessionId + "', " +
                "'" + now() + "', " +
                "'" + now() + "'" +
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
    public static Talker find(long id) throws SQLException, ModelNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet set = statement.executeQuery(
                "select ID, SESSION_ID, " + CREATED_AT + ", " + UPDATED_AT + " " +
                        "from " + MODEL_NAME + " where " + ID + " = " + id
        );

        if (!set.next()) {
            set.close();
            statement.close();
            connection.close();

            throw new ModelNotFoundException(MODEL_NAME, id);
        }

        Talker model = makeInstance(set);

        set.close();
        statement.close();
        connection.close();

        return model;
    }

    /**
     * セッションIDからTalkerを取得
     *
     * @param sessionId セッションID
     * @return Talker
     * @throws SQLException SQLエラー
     */
    public static Talker findBySessionId(String sessionId) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet set = statement.executeQuery(
                "select * from " + MODEL_NAME + " where " + SESSION_ID + " = '" + sessionId + "'"
        );

        if (!set.next()) {
            statement.close();
            connection.close();
            throw new SQLException();
        }

        Talker model = makeInstance(set);

        statement.close();
        connection.close();

        return model;
    }

    /**
     * セッションIDがすでに登録済みであるか確認
     *
     * @param sessionId セッションID
     * @return 登録済みかどうか
     * @throws SQLException SQLエラー
     */
    public static boolean isSessionIdRegistered(String sessionId) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet set = statement.executeQuery(
                "select * from " + MODEL_NAME + " where " + SESSION_ID + " = '" + sessionId + "'"
        );

        boolean flag = set.next();

        statement.close();
        connection.close();

        return flag;
    }

    /**
     * ResultSetからインスタンスを生成
     *
     * @param set Talkersエンティティを検索した結果
     * @return Talkerインスタンス
     * @throws SQLException SQLエラー
     */
    static Talker makeInstance(ResultSet set) throws SQLException {
        return new Talker(
                set.getLong(ID),
                set.getTimestamp(CREATED_AT),
                set.getTimestamp(UPDATED_AT),
                set.getString(SESSION_ID)
        );
    }
}
