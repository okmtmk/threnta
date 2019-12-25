package builder.models;

import builder.queries.Query;
import builder.queries.Where;
import models.Message;

public class MessageQuery extends Query {
    public MessageQuery() {
        super(Message.MODEL_NAME);
    }

    public MessageQuery scopeTalkerId(long talkerId) {
        addWhere(new Where(Message.TALKER_ID, "=", talkerId));
        return this;
    }

    public MessageQuery scopeRoomId(long roomId) {
        addWhere(new Where(Message.ROOM_ID, "=", roomId));
        return this;
    }
}
