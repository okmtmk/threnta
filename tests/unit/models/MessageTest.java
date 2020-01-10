package unit.models;

import exceptions.ModelNotFoundException;
import models.Message;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class MessageTest {
    @Test
    public void testCreate() throws ModelNotFoundException, SQLException {
        Message model = Message.create(1, 1, "test");
        assertEquals(model.getTalkerId(), 1);
    }

    @Test
    public void testFind() throws SQLException, ModelNotFoundException {
        Message model = Message.find(1);
        assertEquals(1, model.getId());
    }

    @Test
    public void testFindWhenFail() {
        try {
            Message.find(999);

            fail();
        } catch (SQLException e) {
            fail();
        } catch (ModelNotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetMessagesByRoomId() throws SQLException {
        List<Message> messages = Message.getMessagesByRoomId(1);
        assertTrue(messages.size() > 0);
    }

    @Test
    public void testGetTalker() throws SQLException, ModelNotFoundException {
        Message message = Message.find(1);

        assertEquals(1, message.getTalker().getId());
    }

    @Test
    public void getRoom() throws ModelNotFoundException, SQLException {
        Message model = Message.find(1);
        assertEquals(1, model.getRoom().getId());
    }

    // レコードの準備が大変なのでスキップ
    public void deleteByRoomId() throws SQLException {
        Message.deleteByRoomId(1);
        assertTrue(true);
    }
}
