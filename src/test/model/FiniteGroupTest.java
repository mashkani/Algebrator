package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FiniteGroupTest {

    Set<CongruenceClass> set;
    int operation;
    FiniteGroup group;

    @BeforeEach
    public void setUp() {
        set = new HashSet<>();
        operation = 1;
        group = new FiniteGroup(set, operation);
    }

    @Test
    public void testConstructor() {

        try {
            group.setOperation(1);
            assertEquals(1, group.getOperation());
        } catch (InvalidParameterException e) {
            fail("exception should not have been thrown");
        }

        try {
            group.setOperation(2);
            assertEquals(2, group.getOperation());
        } catch (InvalidParameterException e) {
            fail("exception should not have been thrown");
        }

        try {
            group.setOperation(5);
        } catch (InvalidParameterException e) {
            // expected
        }
    }

    @Test
    public void testSetOperation() {
        group.setOperation(2);
        assertEquals(2, group.getOperation());

        group.setOperation(1);
        assertEquals(1, group.getOperation());
    }

    @Test
    public void testSetSet() {
        CongruenceClass c1 = new CongruenceClass(1);
        CongruenceClass c2 = new CongruenceClass(2);
        CongruenceClass c3 = new CongruenceClass(3);

        set.add(c1);
        set.add(c2);
        set.add(c3);

        group.setSet(set);

        assertEquals(set, group.getSet());
    }

    @Test
    public void testGetOperation() {
        assertEquals(1, group.getOperation());

        group.setOperation(2);
        assertEquals(2, group.getOperation());
    }

    @Test
    public void testGetSet() {
        assertEquals(set, group.getSet());
    }

    @Test
    public void testAddElement() {
        CongruenceClass c1 = new CongruenceClass(1);
        CongruenceClass c2 = new CongruenceClass(2);
        CongruenceClass c3 = new CongruenceClass(3);


        group.addElement(c1);
        assertEquals(1, set.size());

        group.addElement(c2);
        assertEquals(2, set.size());

        group.addElement(c3);
        assertEquals(3, set.size());
    }

    @Test
    public void testRemoveElement() {
        CongruenceClass c1 = new CongruenceClass(1);
        CongruenceClass c2 = new CongruenceClass(2);
        CongruenceClass c3 = new CongruenceClass(3);

        group.addElement(c1);
        group.removeElement(c2);
        assertEquals(1, set.size());

        group.addElement(c2);
        group.addElement(c3);
        group.removeElement(c1);
        assertEquals(2, set.size());
    }

    @Test
    public void testCountSet() {
        CongruenceClass c1 = new CongruenceClass(1);
        CongruenceClass c2 = new CongruenceClass(2);
        CongruenceClass c3 = new CongruenceClass(3);

        assertEquals(0,group.countSet());

        group.addElement(c1);
        assertEquals(1,group.countSet());

        group.addElement(c2);
        assertEquals(2,group.countSet());

        group.addElement(c3);
        assertEquals(3,group.countSet());

        group.addElement(c3);
        assertEquals(3,group.countSet());
    }

    @Test
    public void testContains() {
        CongruenceClass c1 = new CongruenceClass(1);
        CongruenceClass c2 = new CongruenceClass(2);
        CongruenceClass c3 = new CongruenceClass(3);

        assertFalse(group.contains(c1));
        assertFalse(group.contains(c2));
        assertFalse(group.contains(c3));

        group.addElement(c1);
        assertTrue(group.contains(c1));
        assertFalse(group.contains(c2));
        assertFalse(group.contains(c3));

        group.addElement(c2);
        assertTrue(group.contains(c1));
        assertTrue(group.contains(c2));
        assertFalse(group.contains(c3));

        group.addElement(c3);
        assertTrue(group.contains(c1));
        assertTrue(group.contains(c2));
        assertTrue(group.contains(c3));
    }

/*

    @Test
    public void testIsClosed() {  

        // <{0,1,2,3,4}, +>
        group.setOperation(1);
        for (int i = 0; i < 5; i ++) {
            CongruenceClass c = new CongruenceClass(i);
            group.addElement(c);
        }

        assertEquals(5, group.getSet().size());
        assertTrue(group.isClosed());
    }





    @Test
    public void testIsAssociative() {
        //stub
    }

    @Test
    public void testHasIdentity() {
        //stub
    }

    @Test
    public void testGetIdentity() {
        //stub
    }

    @Test
    public void testHasInverse() {
        //stub
    }

    @Test
    public void testGetInverse() {
        //stub
    }

    @Test
    public void testIsGroup() {
        //stub
    }

    @Test
    public void testIsAbelian() {
        //stub
    }
*/
}