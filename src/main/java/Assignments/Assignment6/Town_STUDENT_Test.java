package Assignments.Assignment6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Town_STUDENT_Test {

    Town testTown;

    @Before
    public void setUp(){
        testTown = new Town("a_town");
    }

    @After
    public void tearDown(){
        testTown = null;
    }

    @Test
    public void testEquality(){
        Town otherTown = new Town("a_town");
        assertTrue(testTown.equals(otherTown));
        assertEquals(testTown.compareTo(otherTown), 0);
        assertEquals(testTown.hashCode(), otherTown.hashCode());
    }

    @Test
    public void testCopy(){
        assertEquals(testTown, new Town(testTown));
    }

    @Test
    public void stringRepresentation(){
        assertEquals(testTown.getName(), "a_town");
        assertEquals(testTown.toString(), "a_town");
    }

}
