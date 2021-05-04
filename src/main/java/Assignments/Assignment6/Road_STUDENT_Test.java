package Assignments.Assignment6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Road_STUDENT_Test {

    Town town1;
    Town town2;
    Town town3;

    Road testRoad;
    Road testRoadWeighted;
    Road testRoad2;

    @Before
    public void setUp(){
        town1 = new Town("town1");
        town2 = new Town("town2");
        town3 = new Town("town3");

        testRoad = new Road(town1, town2, "testroad");
        testRoadWeighted = new Road(town1, town2, 6, "testroad");
        testRoad2 = new Road(town2, town3, "road2");
    }

    @After
    public void tearDown(){
        town1 = null;
        town2 = null;
        town3 = null;
    }

    @Test
    public void testEquality(){
        assertTrue(testRoad.equals(testRoadWeighted));
        assertEquals(testRoad.compareTo(testRoadWeighted), 0);
        assertNotEquals(testRoad, testRoad2);
    }

    @Test
    public void testTownAccess(){
        assertTrue(testRoad.contains(town2));
        assertFalse(testRoad.contains(town3));
        assertEquals(testRoadWeighted.getSource(), town1);
        assertEquals(testRoad2.getDestination(), town3);
    }

    @Test
    public void testWeight(){
        assertEquals(testRoad.getWeight(), 1);
        assertEquals(testRoadWeighted.getWeight(), 6);
    }

    @Test
    public void stringRepresentation(){
        assertEquals(testRoad.getName(), "testroad");
        assertEquals(testRoad2.toString(), "road2");
    }

}
