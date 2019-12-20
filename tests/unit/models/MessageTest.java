package unit.models;

import models.Message;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class MessageTest {
    @Test
    public void testFind() throws SQLException {
        Message model = Message.find(1);
        assertEquals(1, model.getId());
    }
}
