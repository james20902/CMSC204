package Assignments.Assignment6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;


import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TownGraphManager_STUDENT_Test {
    private TownGraphManagerInterface graph;
    private String[] towns;

    @Before
    public void setUp() throws Exception {
        graph = new TownGraphManager();
        towns = new String[9];
        for(int i = 0; i < 9; i++){
            towns[i] = Integer.toString(i);
            graph.addTown(towns[i]);
        }
        graph.addRoad(towns[0], towns[1], 4, "r0");
        graph.addRoad(towns[0], towns[7], 8, "r1");
        graph.addRoad(towns[1], towns[7], 11, "r2");
        graph.addRoad(towns[1], towns[2], 8, "r3");
        graph.addRoad(towns[2], towns[3], 7, "r4");
        graph.addRoad(towns[2], towns[5], 4, "r5");
        graph.addRoad(towns[2], towns[8], 2, "r6");
        graph.addRoad(towns[3], towns[4], 9, "r7");
        graph.addRoad(towns[3], towns[5], 14, "r8");
        graph.addRoad(towns[4], towns[5], 10, "r9");
        graph.addRoad(towns[5], towns[6], 2, "r10");
        graph.addRoad(towns[6], towns[7], 1, "r11");
        graph.addRoad(towns[6], towns[8], 6, "r12");
        graph.addRoad(towns[7], towns[8], 7, "r13");

    }

    @After
    public void tearDown() throws Exception {
        graph = null;
    }

    @Test
    public void testAddRoad() {
        assertTrue(graph.addRoad(towns[1], towns[8], 1, "rsomething"));
        assertEquals(graph.getRoad(towns[1], towns[8]), "rsomething");
    }

    @Test
    public void testGetRoad() {
        assertEquals(graph.getRoad(towns[3], towns[5]), "r8");
        assertEquals(graph.getRoad(towns[2], towns[3]), "r4");
    }

    @Test
    public void testAddTown() {
        assertFalse(graph.containsTown("testing"));
        assertTrue(graph.addTown("testing"));
        assertTrue(graph.containsTown("testing"));
    }

    @Test
    public void testDisjointGraph() {
        graph.addTown("you cant get here lol");
        for(String town : graph.allTowns()){
            assertTrue(graph.getPath(town, "you cant get here lol").isEmpty());
        }
    }

    @Test
    public void testContainsTown() {
        assertTrue(graph.containsTown(towns[8]));
        assertFalse(graph.containsTown(Integer.toString(10)));
    }

    @Test
    public void testContainsRoadConnection() {
        assertTrue(graph.containsRoadConnection(towns[1], towns[7]));
        assertFalse(graph.containsRoadConnection(towns[1], towns[8]));
    }

    @Test
    public void testAllRoads() {
        ArrayList<String> roads = graph.allRoads();
        Collections.sort(roads);
        assertEquals("r0", roads.get(0));
        assertEquals("r1", roads.get(1));
        assertEquals("r8", roads.get(12));
        assertEquals("r9", roads.get(13));
    }

    @Test
    public void testDeleteRoadConnection() {
        assertTrue(graph.containsRoadConnection(towns[2], towns[5]));
        assertTrue(graph.deleteRoadConnection(towns[2], towns[5], "r5"));
        assertFalse(graph.containsRoadConnection(towns[2], towns[5]));
    }

    @Test
    public void testDeleteTown() {
        assertTrue(graph.containsTown(towns[2]));
        assertTrue(graph.deleteTown(towns[2]));
        assertFalse(graph.containsTown(towns[2]));
    }

    @Test
    public void testAllTowns() {
        ArrayList<String> towns = graph.allTowns();
        Collections.sort(towns);
        for(int i = 0; i < 9; i++){
            assertEquals(towns.get(i), Integer.toString(i));
        }
    }

    @Test
    public void testPathTraverse1() {
        ArrayList<String> path = graph.getPath(towns[1], towns[5]);
        assertEquals(path.get(0), "1 via r3 to 2 8 mi");
        assertEquals(path.get(1), "2 via r5 to 5 4 mi");
    }

    @Test
    public void testPathTraverse2() {
        ArrayList<String> path = graph.getPath(towns[1], towns[4]);
        System.out.println(path);
        assertEquals(path.get(0), "1 via r3 to 2 8 mi");
        assertEquals(path.get(1), "2 via r5 to 5 4 mi");
        assertEquals(path.get(2), "5 via r9 to 4 10 mi");
    }
}
