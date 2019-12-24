package builder.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Query {
    Command command;
    List<Where> wheres;

    public Query(Command command) {
        this.command = command;

        wheres = new ArrayList<>();
    }

    public Query addWhere(Where where) {
        wheres.add(where);
        return this;
    }

    public ResultSet get(Statement statement) throws SQLException {
        StringBuilder sql = new StringBuilder(command.getCommand());
        if (wheres.size() > 0) {
            sql.append(" where ");
            for (Where it : wheres) {
                sql.append(it);
                sql.append(" ");
            }
        }
        return statement.executeQuery(sql.toString());
    }
}
