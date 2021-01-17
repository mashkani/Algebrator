package persistence;

import model.FiniteGroup;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FiniteGroup fg = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFiniteGroup() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyFiniteGroup.json");
        try {
            FiniteGroup fg = reader.read();
            assertEquals(Collections.emptySet(), fg.getSet());
            assertEquals(0, fg.countSet());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFiniteGroup() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralFiniteGroup.json");
        try {
            FiniteGroup fg = reader.read();
            assertEquals(4, fg.countSet());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}