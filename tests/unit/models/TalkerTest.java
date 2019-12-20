package unit.models;

import models.Talker;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class TalkerTest {
    @Test
    public void testFind() throws SQLException {
        Talker model = Talker.find(1);

        assertEquals(1, model.getId());
    }
}
