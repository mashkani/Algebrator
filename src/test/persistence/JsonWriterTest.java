package persistence;

import model.FiniteGroup;
import model.CongruenceClass;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFiniteGroup() {
        Set<CongruenceClass> set = new HashSet<>();
        try {
            FiniteGroup fg = new FiniteGroup(set,1);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFiniteGroup.json");
            writer.open();
            writer.write(fg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFiniteGroup.json");
            fg = reader.read();
            assertEquals(set, fg.getSet());
            assertEquals(0, fg.countSet());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFiniteGroup() {
        Set<CongruenceClass> set = new HashSet<>();
        try {
            FiniteGroup fg = new FiniteGroup(set,2);
            fg.addElement(new CongruenceClass(0));
            fg.addElement(new CongruenceClass(1));
            fg.addElement(new CongruenceClass(2));
            fg.addElement(new CongruenceClass(3));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFiniteGroup.json");
            writer.open();
            writer.write(fg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFiniteGroup.json");
            fg = reader.read();
            // rather than comparing the object, compare congruences classes
            assertEquals(4, fg.countSet());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}