package unit.models;

import exceptions.ModelNotFoundException;
import exceptions.SessionIdAlreadyRegisteredException;
import models.Talker;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.junit.Assert.*;

public class TalkerTest {
    @Test
    public void testFind() throws SQLException, ModelNotFoundException {
        Talker model = Talker.find(1);

        assertEquals(1, model.getId());
    }

    @Test
    public void testFindWhenFail() {
        try {
            Talker.find(999);

            fail();
        } catch (SQLException e) {
            fail();
        } catch (ModelNotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testCreate() throws SQLException, SessionIdAlreadyRegisteredException {
        String id = String.valueOf(new Random().nextLong());
        Talker model = Talker.create(id);
        assertEquals(id, model.getSessionId());
    }

    @Test
    public void testCreateExceptFail() {
        try {
            Talker.create("test");

            fail();
        } catch (SQLException e) {
            fail();
        } catch (SessionIdAlreadyRegisteredException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIsSessionRegistered() throws SQLException {
        assertTrue(Talker.isSessionIdRegistered("test"));
    }

    @Test
    public void testGetCreatedRooms() throws ModelNotFoundException, SQLException {
        Talker talker = Talker.find(1);

        assertEquals(1, talker.getCreatedRooms(1).get(0).getId());
        assertTrue(talker.getCreatedRooms(1).size() > 0);
    }

    @Test
    public void testGetTalkedRooms() throws ModelNotFoundException, SQLException {
        Talker talker = Talker.find(1);
        assertEquals(1, talker.getTalkedRooms().get(0).getId(), 1);
        assertTrue(talker.getTalkedRooms().size() > 0);
    }

    @Test
    public void testFindBySessionId() throws SQLException {
        Talker talker = Talker.findBySessionId("7EE77DDC9CDE1829065A3813AE06069A");

        assertEquals("7EE77DDC9CDE1829065A3813AE06069A", talker.getSessionId());
    }
}
