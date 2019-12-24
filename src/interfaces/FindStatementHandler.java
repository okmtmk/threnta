package interfaces;


import exceptions.ModelNotFoundException;
import models.Model;

import java.sql.SQLException;
import java.sql.Statement;

public interface FindStatementHandler {
    public Model execute(Statement statement) throws SQLException, ModelNotFoundException;
}
