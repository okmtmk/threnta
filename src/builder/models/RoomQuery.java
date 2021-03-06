package builder.models;

import builder.queries.Query;
import builder.queries.Where;
import models.Room;

public class RoomQuery extends Query {
    public RoomQuery() {
        super(Room.MODEL_NAME);
    }

    public RoomQuery scopeCreateTalkerId(long talkerId) {
        addWhere(new Where(Room.CREATE_TALKER_ID, "=", talkerId));
        return this;
    }

    public RoomQuery scopeRoomName(String name) {
        addWhere(new Where(Room.NAME, "like", "%" + name + "%"));
        return this;
    }
}
