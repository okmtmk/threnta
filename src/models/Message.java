package models;

import builder.models.MessageQuery;
import exceptions.ModelNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Message extends Model {
    public static final String MODEL_NAME = "MESSAGES";

    public static final String TALKER_ID = "TALKER_ID";
    public static final String ROOM_ID = "ROOM_ID";
    public static final String MESSAGE = "MESSAGE";

    protected long talkerId;
    protected long roomId;
    protected String message;

    public Message(
            long id, Timestamp createdAt, Timestamp updatedAt, long talkerId, long roomId, String message
    ) {
        super(id, createdAt, updatedAt);
        this.talkerId = talkerId;
        this.roomId = roomId;
        this.message = message;
    }

     /*
    Properties
     */

    public long getTalkerId() {
        return talkerId;
    }

    public long getRoomId() {
        return roomId;
    }

    public String getMessage() {
        return message;
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
    public static Message find(long id) throws SQLException, ModelNotFoundException {
        return (Message)executeFind(statement -> {
            ResultSet set = select().scopeId(id).get(statement);
            return makeInstance(set);
        });
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

    /*
    Queries
     */

    public static MessageQuery select() {
        return new MessageQuery();
    }

    public static List<Message> getMessagesByRoomId(long roomId, long limit) throws SQLException {
        List<Message> messages = new ArrayList<>();
        executeSQL(statement -> {
            MessageQuery query = Message.select().scopeRoomId(roomId);
            ResultSet set;
            if (limit > 0) {
                set = query.limit(limit).get(statement);
            } else {
                set = query.get(statement);
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
    Relations
     */

    public Talker getTalker() throws SQLException, ModelNotFoundException {
        return Talker.find(talkerId);
    }

    public Room getRoom() throws SQLException, ModelNotFoundException {
        return Room.find(roomId);
    }
}
