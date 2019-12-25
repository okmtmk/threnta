package builder.models;

import builder.queries.Query;
import builder.queries.Where;
import models.Message;
import models.Model;
import models.Room;

public class RoomQuery extends Query {
    public RoomQuery() {
        super(Room.MODEL_NAME);
    }

    public RoomQuery scopeCreateTalkerId(long talkerId) {
        addWhere(new Where(Room.CREATE_TALKER_ID, "=", talkerId));
        return this;
    }

    public RoomQuery scopeTalkedTalkerId(long talkerId) {
        addWhere(
                new Where(
                        Model.ID,
                        "=",
                        "select " + Message.ROOM_ID + " from " + Message.MODEL_NAME +
                                " where " + Message.TALKER_ID + " = " + talkerId
                )
        );
        return this;
    }
}
