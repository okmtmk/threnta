package unit.models;

import exceptions.ModelNotFoundException;
import models.Room;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class RoomTest {
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
}
