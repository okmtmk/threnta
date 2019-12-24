package builder.models.room;

import builder.queries.Query;
import builder.queries.Where;
import models.Message;

public class MessageQuery extends Query {
    public MessageQuery() {
        super(Message.MODEL_NAME);
    }

    public MessageQuery scopeTalkerId(long talker_id) {
        addWhere(new Where(Message.TALKER_ID, "=", talker_id));
        return this;
    }


}
