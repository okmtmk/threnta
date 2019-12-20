package unit.models;

import exceptions.ModelNotFoundException;
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
    public void testCreate() throws SQLException {
        String id = String.valueOf(new Random().nextLong());
        Talker model = Talker.create(id);
        assertEquals(id, model.getSessionId());
    }
}
