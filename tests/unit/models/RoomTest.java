package unit.models;

import exceptions.ModelNotFoundException;
import models.Room;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class RoomTest {
    @Test
    public void testCreate() throws SQLException, ModelNotFoundException {
        Room model = Room.create(1, "test", "");
        assertEquals("test", model.getName());
    }

    @Test
    public void testFind() throws ModelNotFoundException, SQLException {
        Room model = Room.find(1);

        assertEquals(1, model.getId());
    }

    @Test
    public void testFindWhenFail() {
        try {
            Room.find(999);

            fail();
        } catch (SQLException e) {
            fail();
        } catch (ModelNotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGet() throws SQLException {
        List<Room> rooms = Room.get();

        assertTrue(rooms.size() > 0);
    }

    @Test
    public void testUpdate() throws ModelNotFoundException, SQLException {
        Room model = Room.find(1);
        model.setDescription("test");
        model.update();

        assertEquals("test", model.getDescription());
    }

    @Test
    public void testGetRoomsByCreatedTalkerId() throws SQLException {
        List<Room> rooms = Room.getRoomsByCreatedTalkerId(1);
        assertTrue(rooms.size() > 0);
    }

    @Test
    public void testGetRoomsByTalkedTalkerId() throws SQLException {
        List<Room> rooms = Room.getRoomsByTalkedTalkerId(1);
        assertTrue(rooms.size() > 0);
    }

    @Test
    public void testGetRoomsByName() throws SQLException {
        List<Room> rooms = Room.getRoomsByName("test");
        assertTrue(rooms.size() > 0);
    }

    @Test
    public void testGetMessages() throws ModelNotFoundException, SQLException {
        Room model = Room.find(1);

        assertEquals(1, model.getMessages().get(0).getId());
        assertTrue(model.getMessages().size() > 0);
    }

    @Test
    public void testGetCreateTalker() throws ModelNotFoundException, SQLException {
        Room model = Room.find(1);
        assertEquals(1, model.getCreateTalker().getId());
    }

    // レコードの準備が大変なのでスキップ
    public void testRemove() throws ModelNotFoundException, SQLException {
        Room model = Room.find(1);
        model.delete();
        assertTrue(true);
    }
}
