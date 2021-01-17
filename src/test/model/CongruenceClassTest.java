package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CongruenceClassTest {
    CongruenceClass cc;

    @BeforeEach
    public void setUp() {
        cc = new CongruenceClass(1);
    }

    @Test
    public void testGetCongruenceClass() {
        cc.setCongruenceClass(2);
        assertEquals(2,cc.getCongruenceClass());
    }

    @Test
    public void testSetCongruenceClass(){
        cc.setCongruenceClass(2);
        assertEquals(2, cc.getCongruenceClass());
    }
}