package models;

import builder.models.room.RoomQuery;
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
        List<Message> messages = new ArrayList<>();
        executeSQL(statement -> {

            ResultSet set;
            if (limit > 0) {
                set = Message.select().limit(limit).get(statement);
            } else {
                set = Message.select().get(statement);
            }
            while (set.next()) {
                messages.add(
                        Message.makeInstance(set)
                );
            }
        });
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

    public static Room create(long createTalkerId, String name, String description) throws SQLException {
        final List<Room> rooms = new ArrayList<>();
        executeSQL(statement -> {
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

            try {
                long id = getLastInsertedId(MODEL_NAME, statement);
                rooms.add(find(id));
            } catch (Exception e) {
                throw new SQLException();
            }
        });
        return rooms.get(0);
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

    public static List<Room> index() throws SQLException {
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

    public static RoomQuery select() {
        return new RoomQuery();
    }
}
