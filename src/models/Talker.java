package models;

import builder.models.TalkerQuery;
import exceptions.ModelNotFoundException;
import exceptions.SessionIdAlreadyRegisteredException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

    /*
    Static methods
     */

    /**
     * ResultSetからインスタンスを生成
     *
     * @param set Talkersエンティティを検索した結果
     * @return Talkerインスタンス
     * @throws SQLException SQLエラー
     */
    private static Talker makeInstance(ResultSet set) throws SQLException {
        return new Talker(
                set.getLong(ID),
                set.getTimestamp(CREATED_AT),
                set.getTimestamp(UPDATED_AT),
                set.getString(SESSION_ID)
        );
    }

    /**
     * Talkerを作成
     *
     * @param sessionId セッションID
     * @return 作成されたTalker
     * @throws SQLException                        SQLエラー
     * @throws SessionIdAlreadyRegisteredException セッションIDが登録済み
     */
    public static Talker create(String sessionId) throws SQLException, SessionIdAlreadyRegisteredException, ModelNotFoundException {
        if (isSessionIdRegistered(sessionId)) {
            throw new SessionIdAlreadyRegisteredException(sessionId);
        }

        return (Talker) executeFind(statement -> {
            //language=sql
            String sql = "insert into " + MODEL_NAME + "(" + SESSION_ID + ", " + CREATED_AT + ", " + UPDATED_AT + ") " +
                    "values (" +
                    "'" + sessionId + "', " +
                    "'" + now() + "', " +
                    "'" + now() + "'" +
                    ")";
            if (statement.executeLargeUpdate(sql) != 1) {
                throw new SQLException();
            }

            long id = getLastInsertedId(MODEL_NAME, statement);
            return find(id);
        });
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
        return (Talker) executeFind(statement -> {
            ResultSet set = select().scopeId(id).get(statement);

            if (!set.next()) {
                throw new ModelNotFoundException(MODEL_NAME, id);
            }

            return makeInstance(set);
        });
    }

    /**
     * セッションIDからTalkerを取得
     *
     * @param sessionId セッションID
     * @return Talker
     * @throws SQLException SQLエラー
     */
    public static Talker findBySessionId(String sessionId) throws SQLException, ModelNotFoundException {
        return (Talker) executeFind(statement -> {
            ResultSet set = select().scopeSessionId(sessionId).get(statement);

            if (!set.next()) {
                throw new ModelNotFoundException(MODEL_NAME, -1);
            }

            return makeInstance(set);
        });
    }

    /**
     * セッションIDがすでに登録済みであるか確認
     *
     * @param sessionId セッションID
     * @return 登録済みかどうか
     * @throws SQLException SQLエラー
     */
    public static boolean isSessionIdRegistered(String sessionId) throws SQLException {
        try {
            findBySessionId(sessionId);
        } catch (ModelNotFoundException e) {
            return false;
        }

        return true;
    }

    /*
    Queries
     */

    public static TalkerQuery select() {
        return new TalkerQuery();
    }

    /*
    Relations
     */

    /**
     * Talkerが作成したルームを取得
     *
     * @return Talkerが作成したルーム
     * @throws SQLException SQLエラー
     */
    public List<Room> getCreatedRooms() throws SQLException {
        return Room.getRoomsByCreatedTalkerId(id);
    }

    /**
     * 自分の発言があるルームを取得
     *
     * @return 自分の発言があるルーム
     * @throws SQLException SQLエラ−
     */
    public List<Room> getTalkedRooms() throws SQLException {
        return Room.getRoomsByTalkedTalkerId(id);
    }
}
