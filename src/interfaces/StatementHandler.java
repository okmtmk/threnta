package interfaces;

import java.sql.SQLException;
import java.sql.Statement;

public interface StatementHandler {
    public void execute(Statement statement) throws SQLException;
}
