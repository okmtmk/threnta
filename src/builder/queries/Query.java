package builder.queries;

import builder.models.room.RoomQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class Query {
    private String command;
    private List<Where> wheres;
    private Limit limit = null;

    public Query(String modelName) {
        this.command = "select * from " + modelName;

        wheres = new ArrayList<>();
    }

    public void addWhere(Where where) {
        wheres.add(where);
    }

    public void addLimit(Limit limit) {
        this.limit = limit;
    }

    public ResultSet get(Statement statement) throws SQLException {
        StringBuilder sql = new StringBuilder(command);
        boolean isMulti = false;

        if (wheres.size() > 0) {
            sql.append(" where");
            for (Where it : wheres) {
                if (isMulti) {
                    sql.append(" AND ");
                } else {
                    sql.append(" ");
                    isMulti = true;
                }
                sql.append(it.getCommand());
            }
        }

        if (limit != null) {
            sql.append(" ");
            sql.append(limit.getCommand());
        }

        return statement.executeQuery(sql.toString());
    }

    public Query scopeId(long id) {
        addWhere(new Where("id", "=", id));

        return this;
    }

    public Query limit(long limit) {
        addLimit(new Limit(limit));
        return this;
    }
}
