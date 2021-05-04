package Assignments.Assignment6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Hashmap/adjacency list based graph implementation, with vertices represented as towns
 * and edges represented as roads.
 *
 * @author James Pham
 */
public class Graph implements GraphInterface<Town, Road>{

    HashMap<Town, Set<Road>> map;
    HashMap<Town, Town> pointerMap;
    HashMap<Town, Integer> shortestPathMap;
    ArrayList<String> path;
    Town globalEnd;
    StringBuilder builder;

    /**
     * No param constructor for hashmap creation
     */
    public Graph(){
        map = new HashMap<>();
        builder = new StringBuilder();
    }

    /**
     * Add connections to hashmap based on formatted file
     * @param file formatted file
     * @throws FileNotFoundException if given file does not exist
     */
    public void populateTownGraph(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        //add vertices and edges based on formatted split
        reader.lines().forEach((line) -> {
            String[] params = line.split("[!^;,]");
            addVertex(new Town(params[2]));
            addVertex(new Town(params[3]));
            addEdge(new Town(params[2]), new Town(params[3]), Integer.parseInt(params[1]), params[0]);
        });
    }

    /**
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return road object with the appropriate source and destination vertices or null if doesn't exist
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        if(!containsVertex(sourceVertex)){
            return null;
        }
        for(Road r : map.get(sourceVertex)){
            if(r.equals(new Road(sourceVertex, destinationVertex, ""))){
                return r;
            }
        }
        return null;
    }

    /**
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description name of edge
     *
     * @return the added road
     */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        Road road1 = new Road(sourceVertex, destinationVertex, weight, description);
        Road road2 = new Road(destinationVertex, sourceVertex, weight, description);
        map.get(sourceVertex).add(road1);
        map.get(destinationVertex).add(road2);
        return road1;
    }

    /**
     * @param town vertex to add
     * @return whether or not the vertex was added successfully
     */
    @Override
    public boolean addVertex(Town town) {
        if(map.containsKey(town)){
            return false;
        }
        map.put(town, new HashSet<>());
        return true;
    }

    /**
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return whether or not the road exists in the map
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        for(Road r : map.get(sourceVertex)){
            if(r.equals(new Road(sourceVertex, destinationVertex, ""))){
                return true;
            }
        }
        return false;
    }

    /**
     * @param town vertex to search for
     * @return whether or not the vertex is defined in the map
     */
    @Override
    public boolean containsVertex(Town town) {
        return map.containsKey(town);
    }

    /**
     * @return all the edges in the graph represented as a unique set
     */
    @Override
    public Set<Road> edgeSet() {
        //to remove duplicates caused by bidirectionality
        //im not proud
        Set<Road> set = new HashSet<>();
        map.forEach((town, roads) -> {
            boolean contains = false;
            for(Road r : roads){
                for(Road check : set){
                    if(r.equals(check)){
                        contains = true;
                        break;
                    }
                }
                if(!contains){
                    set.add(r);
                } else {
                    contains = false;
                }
            }
        });
        return set;
    }

    /**
     * @param vertex vertex to search
     * @return the road objects the vertex connects to other vertices with
     */
    @Override
    public Set<Road> edgesOf(Town vertex) {
        return map.get(vertex);
    }

    /**
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return the removed edge
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        Road dummy = new Road(sourceVertex, destinationVertex, weight, description);
        map.get(sourceVertex).removeIf((road) -> road.equals(dummy) &&
                road.getWeight() == dummy.getWeight() &&
                road.getName().equals(dummy.getName()));
        return dummy;
    }

    /**
     * @param town vertex to remove
     * @return whether or not the vertex was successfully removed
     */
    @Override
    public boolean removeVertex(Town town) {
        map.remove(town);
        return true;
    }

    /**
     * @return all the vertices in the graph represented as a unique set
     */
    @Override
    public Set<Town> vertexSet() {
        return map.keySet();
    }

    /**
     * Wrapper call for execution of Dijkstra's algorithm to traverse graph
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return shortest path traversal represented as a list
     */
    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        map.keySet().forEach((town -> town.visited = false));
        path = new ArrayList<>();
        pointerMap = new HashMap<>();

        //set weight from source to infinity for all elements
        shortestPathMap = new HashMap<>();
        for(Town t : map.keySet()){
            shortestPathMap.put(t, Integer.MAX_VALUE);
        }
        shortestPathMap.put(sourceVertex, 0);
        //set weight from source to adjacencies to road values
        //might as well get it out of the way
        for(Road r : map.get(sourceVertex)){
            shortestPathMap.put(r.getDestination(), r.getWeight());
            pointerMap.put(r.getDestination(), sourceVertex);
        }

        sourceVertex.visited = true;
        globalEnd = destinationVertex;
        dijkstraShortestPath(sourceVertex);
        //to accommodate for the backwards traversal of the algorithm im using
        //im not proud
        Collections.reverse(path);
        return path;
    }

    /**
     * Dijkstra's algorithm for graph traversal given a source vertex
     * @param sourceVertex the vertex to find shortest path from
     */
    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        while(true){
            //get closest town
            int shortestDistance = Integer.MAX_VALUE;
            Town closestReachableTown = null;
            for (Town t : map.keySet()) {
                if (!t.visited && (shortestPathMap.get(t) != Integer.MAX_VALUE)){
                    int currentDistance = shortestPathMap.get(t);
                    if (currentDistance < shortestDistance) {
                        shortestDistance = currentDistance;
                        closestReachableTown = t;
                    }
                }
            }
            if(closestReachableTown == null){
                return;
            }
            //if we have our path, traverse the pointer map
            if(closestReachableTown.equals(globalEnd)){
                //earlier we set the end to loop back at the start
                Town nextPointer = closestReachableTown;
                Town currentPointer;
                while(true){
                    currentPointer = pointerMap.get(nextPointer);
                    if(currentPointer == null){
                        break;
                    }
                    Road fetch = getEdge(currentPointer, nextPointer);
                    builder.append(currentPointer).append(" via ")
                            .append(fetch).append(" to ")
                            .append(nextPointer).append(" ")
                            .append(fetch.getWeight()).append(" mi");
                    path.add(builder.toString());
                    builder.setLength(0);
                    nextPointer = currentPointer;
                }
                return;
            } else {
                //otherwise, note visited
                closestReachableTown.visited = true;

                //for all adjacent towns
                for(Road r : map.get(closestReachableTown)){
                    if(!r.getDestination().visited){
                        //if the path from where we're at + how far it would take to get to a given town
                        //is less than some other path to our given destination
                        if(shortestPathMap.get(closestReachableTown) + r.getWeight() < shortestPathMap.get(r.getDestination())){
                            //replace the infinite weight and log the best way to get from the given destination to the
                            //closest town
                            shortestPathMap.put(r.getDestination(), shortestPathMap.get(closestReachableTown) + r.getWeight());
                            pointerMap.put(r.getDestination(), closestReachableTown);
                        }
                    }
                }
            }
        }
    }

}
