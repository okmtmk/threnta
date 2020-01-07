package builder.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class Query {
    private String command;
    private List<Where> wheres;
    private Offset offset = null;
    private Limit limit = null;
    private OrderBy orderBy = null;

    public Query(String modelName) {
        this.command = "select * from " + modelName;

        wheres = new ArrayList<>();
    }

    public void addWhere(Where where) {
        wheres.add(where);
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

    public void setLimit(Limit limit) {
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

        if (offset != null) {
            sql.append(" ");
            sql.append(offset.getCommand());
        }

        if (limit != null) {
            sql.append(" ");
            sql.append(limit.getCommand());
        }

        if (orderBy != null) {
            sql.append(" ");
            sql.append(orderBy.getCommand());
        }

//        System.out.println(sql);
        return statement.executeQuery(sql.toString());
    }

    public Query scopeId(long id) {
        addWhere(new Where("id", "=", id));

        return this;
    }

    public Query limit(long limit) {
        setLimit(new Limit(limit));
        return this;
    }

    public Query desc(String column) {
        orderBy = new OrderBy(column, "desc");
        return this;
    }

    public Query asc(String column) {
        orderBy = new OrderBy(column, "asc");
        return this;
    }
}
