package Assignments.Assignment6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class Graph_STUDENT_Test {
    private GraphInterface<Town,Road> graph;
    private Town[] towns;

    @Before
    public void setUp() throws Exception {
        graph = new Graph();
        towns = new Town[9];
        for(int i = 0; i < 9; i++){
            towns[i] = new Town(Integer.toString(i));
            graph.addVertex(towns[i]);
        }
        graph.addEdge(towns[0], towns[1], 4, "r0");
        graph.addEdge(towns[0], towns[7], 8, "r1");
        graph.addEdge(towns[1], towns[7], 11, "r2");
        graph.addEdge(towns[1], towns[2], 8, "r3");
        graph.addEdge(towns[2], towns[3], 7, "r4");
        graph.addEdge(towns[2], towns[5], 4, "r5");
        graph.addEdge(towns[2], towns[8], 2, "r6");
        graph.addEdge(towns[3], towns[4], 9, "r7");
        graph.addEdge(towns[3], towns[5], 14, "r8");
        graph.addEdge(towns[4], towns[5], 10, "r9");
        graph.addEdge(towns[5], towns[6], 2, "r10");
        graph.addEdge(towns[6], towns[7], 1, "r11");
        graph.addEdge(towns[6], towns[8], 6, "r12");
        graph.addEdge(towns[7], towns[8], 7, "r13");

    }

    @After
    public void tearDown() throws Exception {
        graph = null;
    }

    @Test
    public void testGetEdge() {
        assertEquals(graph.getEdge(towns[3], towns[5]).toString(), "r8");
        assertNull(graph.getEdge(towns[1], towns[8]));
    }

    @Test
    public void testAddEdge() {
        assertNull(graph.getEdge(towns[1], towns[8]));
        assertNotNull(graph.addEdge(towns[1], towns[8], 1, "rsomething"));
        assertEquals(graph.getEdge(towns[1], towns[8]).toString(), "rsomething");
    }

    @Test
    public void testAddVertex() {
        Town test = new Town("testing");
        assertFalse(graph.containsVertex(test));
        assertTrue(graph.addVertex(test));
        assertTrue(graph.containsVertex(test));
    }

    @Test
    public void testContainsEdge() {
        assertTrue(graph.containsEdge(towns[1], towns[7]));
        assertFalse(graph.containsEdge(towns[1], towns[8]));
    }

    @Test
    public void testContainsVertex() {
        assertTrue(graph.containsVertex(towns[8]));
        assertFalse(graph.containsVertex(new Town(Integer.toString(10))));
    }

    @Test
    public void testEdgeSet() {
        Set<Road> roads = graph.edgeSet();
        ArrayList<String> roadArrayList = new ArrayList<String>();
        roads.forEach((road -> roadArrayList.add(road.toString())));
        Collections.sort(roadArrayList);
        assertEquals("r0", roadArrayList.get(0));
        assertEquals("r1", roadArrayList.get(1));
        assertEquals("r8", roadArrayList.get(12));
        assertEquals("r9", roadArrayList.get(13));
    }

    @Test
    public void testEdgesOf() {
        Set<Road> roads = graph.edgesOf(towns[0]);
        ArrayList<String> roadArrayList = new ArrayList<String>();
        roads.forEach((road -> roadArrayList.add(road.toString())));
        Collections.sort(roadArrayList);
        assertEquals("r0", roadArrayList.get(0));
        assertEquals("r1", roadArrayList.get(1));
    }

    @Test
    public void testRemoveEdge() {
        assertTrue(graph.containsEdge(towns[2], towns[5]));
        assertNotNull(graph.removeEdge(towns[2], towns[5], 4, "r5"));
        assertFalse(graph.containsEdge(towns[2], towns[5]));
    }

    @Test
    public void testRemoveVertex() {
        assertTrue(graph.containsVertex(towns[2]));
        assertTrue(graph.removeVertex(towns[2]));
        assertFalse(graph.containsVertex(towns[2]));
    }

    @Test
    public void testVertexSet() {
        Set<Town> towns = graph.vertexSet();
        ArrayList<String> vertexList = new ArrayList<String>();
        towns.forEach((town -> vertexList.add(town.toString())));
        Collections.sort(vertexList);
        for(int i = 0; i < 9; i++){
            assertEquals(vertexList.get(i), Integer.toString(i));
        }
    }

    @Test
    public void testPathTraverse1() {
        ArrayList<String> path = graph.shortestPath(towns[0], towns[8]);
        assertEquals(path.get(0), "0 via r0 to 1 4 mi");
        assertEquals(path.get(1), "1 via r3 to 2 8 mi");
        assertEquals(path.get(2), "2 via r6 to 8 2 mi");

    }

    @Test
    public void testPathTraverse2(){
        ArrayList<String> path = graph.shortestPath(towns[0], towns[4]);
        assertEquals(path.get(0), "0 via r1 to 7 8 mi");
        assertEquals(path.get(1), "7 via r11 to 6 1 mi");
        assertEquals(path.get(2), "6 via r10 to 5 2 mi");
        assertEquals(path.get(3), "5 via r9 to 4 10 mi");
    }

}
