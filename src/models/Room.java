package models;

import builder.models.RoomQuery;
import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Room extends Model {
    public static final String MODEL_NAME = "ROOMS";

    public static final String CREATE_TALKER_ID = "CREATE_TALKER_ID";
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";

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
        executeSQL(statement -> {
            if (statement.executeUpdate(
                    "update " + MODEL_NAME +
                            " set " + NAME + " = '" + name + "', " +
                            DESCRIPTION + " = '" + description + "', " +
                            UPDATED_AT + " = '" + now() + "'" +
                            "where " + ID + " = " + id
            ) != 1) {
                throw new SQLException();
            }
        });
        return this;
    }

    /*
    Static methods
     */

    public static Room create(long createTalkerId, String name, String description) throws SQLException, ModelNotFoundException {
        return (Room) executeFind(statement -> {
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
                throw new SQLException();
            }

            long id = getLastInsertedId(MODEL_NAME, statement);
            return find(id);
        });
    }

    public static Room find(long id) throws SQLException, ModelNotFoundException {
        return (Room) executeFind(statement -> {
            ResultSet set = select().scopeId(id).get(statement);

            if (!set.next()) {
                throw new ModelNotFoundException(MODEL_NAME, id);
            }
            return makeInstance(set);
        });
    }

    public static List<Room> get() throws SQLException {
        List<Room> rooms = new ArrayList<>();

        executeSQL(statement -> {
            ResultSet set = select().desc(Room.CREATED_AT).get(statement);
            while (set.next()) {
                rooms.add(Room.makeInstance(set));
            }
        });

        return rooms;
    }

    /**
     * ResultSetからインスタンスを作成する。
     *
     * @param set Roomを検索した結果
     * @return Roomインスタンス
     * @throws SQLException SQLエラー
     */
    static Room makeInstance(ResultSet set) throws SQLException {
        return new Room(
                set.getLong(ID),
                set.getTimestamp(CREATED_AT),
                set.getTimestamp(UPDATED_AT),
                set.getLong(CREATE_TALKER_ID),
                set.getString(NAME),
                set.getString(DESCRIPTION)
        );
    }

    /*
    Queries
     */

    public static RoomQuery select() {
        return new RoomQuery();
    }

    public static List<Room> getRoomsByCreatedTalkerId(long createdTalkerId) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        executeSQL(statement -> {
            ResultSet set = select().scopeCreateTalkerId(createdTalkerId).get(statement);

            while (set.next()) {
                rooms.add(makeInstance(set));
            }
        });
        return rooms;
    }

    public static List<Room> getRoomsByTalkedTalkerId(long talkerId) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        executeSQL(statement -> {
            ResultSet set = statement.executeQuery(
                    "select * from ROOMS where " + ID + " = " +
                            "(select " + Message.ROOM_ID + " from " + Message.MODEL_NAME +
                            " where " + Message.TALKER_ID + " = " + talkerId + ")"
            );
            while (set.next()) {
                rooms.add(makeInstance(set));
            }
        });
        return rooms;
    }

    /*
    Relations
     */

    /**
     * 関連するメッセージを指定件数取得
     *
     * @return メッセージ
     * @throws SQLException SQLエラー
     */
    public List<Message> getMessages() throws SQLException {
        return Message.getMessagesByRoomId(id);
    }

    /**
     * 作成ユーザの取得
     *
     * @return スレッド作成ユーザ
     * @throws SQLException           SQLエラー
     * @throws ModelNotFoundException 作成ユーザが見つからないエラー
     */
    public Talker getCreateTalker() throws SQLException, ModelNotFoundException {
        return Talker.find(createTalkerId);
    }
}
