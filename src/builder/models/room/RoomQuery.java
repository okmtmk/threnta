package builder.models.room;

import builder.queries.Limit;
import builder.queries.Query;
import builder.queries.Where;
import models.Room;

public class RoomQuery extends Query {
    public RoomQuery() {
        super(Room.MODEL_NAME);
    }

    public RoomQuery scopeCreateTalkerId(long talkerId) {
        addWhere(new Where("create_talker_id", "=", talkerId));
        return this;
    }

    public RoomQuery limit(long limit) {
        addLimit(new Limit(limit));
        return this;
    }
}
