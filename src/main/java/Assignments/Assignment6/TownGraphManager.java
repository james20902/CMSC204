package Assignments.Assignment6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Generic API-friendly graph wrapper
 *
 * @author James Pham
 */
public class TownGraphManager implements TownGraphManagerInterface{

    Graph graph;

    /**
     * Constructor to initialize graph
     */
    public TownGraphManager(){
        graph = new Graph();
    }

    /**
     * @param town1 starting town
     * @param town2 ending town
     * @param weight cost to travel
     * @param roadName name of road
     * @return whether or not the road was successfully added
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        return graph.addEdge(new Town(town1), new Town(town2), weight, roadName) != null;
    }

    /**
     * @param town1 name of starting town
     * @param town2 name of destination town
     * @return name of road that connects the given towns
     */
    @Override
    public String getRoad(String town1, String town2) {
        return graph.getEdge(new Town(town1), new Town(town2)).getName();
    }

    /**
     * @param v the town's name
     * @return whether or not the town was successfully added
     */
    @Override
    public boolean addTown(String v) {
        return graph.addVertex(new Town(v));
    }

    /**
     * @param name the town's name
     * @return object representing the town
     */
    @Override
    public Town getTown(String name) {
        for(Town t : graph.vertexSet()){
            if(t.equals(new Town(name))){
                return t;
            }
        }
        return null;
    }

    /**
     * @param v the town's name
     * @return whether or not the town exists within the graph
     */
    @Override
    public boolean containsTown(String v) {
        return graph.containsVertex(new Town(v));
    }

    /**
     * @param town1 starting town
     * @param town2 ending town
     * @return whether or not theres a road connecting the two towns
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        return graph.containsEdge(new Town(town1), new Town(town2));
    }

    /**
     * @return list of the name of all roads in the graph
     */
    @Override
    public ArrayList<String> allRoads() {
        ArrayList<String> storage = new ArrayList<>();
        graph.edgeSet().stream().sorted().forEach((road) -> storage.add(road.toString()));
        return storage;
    }

    /**
     * @param town1 starting town
     * @param town2 ending town
     * @param road name of the road
     * @return whether or not the road was deleted successfully
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {
        return graph.removeEdge(new Town(town1),
                new Town(town2),
                graph.getEdge(new Town(town1), new Town(town2)).getWeight(),
                road) != null;
    }

    /**
     * @param v name of town
     * @return whether or not the town was deleted successfully
     */
    @Override
    public boolean deleteTown(String v) {
        return graph.removeVertex(new Town(v));
    }

    /**
     * @return list of the name of all towns in the graph
     */
    @Override
    public ArrayList<String> allTowns() {
        ArrayList<String> storage = new ArrayList<>();
        graph.vertexSet().stream().sorted().forEach((town) -> storage.add(town.toString()));
        return storage;
    }

    /**
     * @param town1 starting town
     * @param town2 destination town
     * @return list containing directions to get to destination in best way possible
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        return graph.shortestPath(new Town(town1), new Town(town2));
    }

    /**
     * load a town graph based on a formatted file
     * @param selectedFile file to laod
     * @throws FileNotFoundException if file doesn't exist
     * @throws IOException if file is corrupted
     */
    public void populateTownGraph(File selectedFile) throws FileNotFoundException,
            IOException {
        graph.populateTownGraph(selectedFile);
    }
}
