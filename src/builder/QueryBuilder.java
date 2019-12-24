package builder;

import builder.queries.Query;
import builder.queries.Select;

public class QueryBuilder {
    public static Query select(String model) {
        return new Query(new Select(model));
    }
}
