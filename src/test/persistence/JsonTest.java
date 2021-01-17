package persistence;

import model.FiniteGroup;
import model.CongruenceClass;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {
    protected void checkFiniteGroup(Set<CongruenceClass> set, int operation, FiniteGroup finiteGroup) {
        assertEquals(operation, finiteGroup.getOperation());
        assertEquals(set, finiteGroup.getSet());
    }
}