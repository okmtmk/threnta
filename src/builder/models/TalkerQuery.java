package builder.models;

import builder.queries.Query;
import builder.queries.Where;
import models.Talker;

public class TalkerQuery extends Query {
    public TalkerQuery() {
        super(Talker.MODEL_NAME);
    }

    public TalkerQuery scopeSessionId(String sessionId) {
        addWhere(new Where(Talker.SESSION_ID, "=", sessionId));
        return this;
    }
}
