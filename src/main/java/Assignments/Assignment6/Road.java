package Assignments.Assignment6;


/**
 * Road data structure for graph edge representation
 */
public class Road implements Comparable<Road>{

    Town source, destination;
    int weight;
    String name;

    /**
     * Constructor
     * @param source starting town
     * @param destination ending town
     * @param degrees cost to travel
     * @param name name of street
     */
    public Road(Town source, Town destination, int degrees, String name){
        this.source = source;
        this.destination = destination;
        this.weight = degrees;
        this.name = name;
    }

    /**
     * Simplified constructor with assumed weight of 1
     * @param source starting town
     * @param destination ending town
     * @param name name of street
     */
    public Road(Town source, Town destination, String name){
        this(source, destination, 1, name);
    }

    /**
     * Comparison method for iteration
     * @param o road to compare
     * @return 0 if matching, other if not
     */
    @Override
    public int compareTo(Road o) {
        return name.compareTo(o.getName());
    }

    /**
     * @param town town to look for
     * @return true if road leads to town
     */
    public boolean contains(Town town){
        return destination.equals(town);
    }

    /**
     * @param o road to compare
     * @return true if sources and destinations match (or vice versa)
     */
    public boolean equals(Object o){
        Road r = (Road)o;
        return ((source.equals(r.getSource())) &&
                (destination.equals(r.getDestination()))) ||
                ((source.equals(r.getDestination())) &&
                        (destination.equals(r.getSource())));
    }

    /**
     * @return Town destination
     */
    public Town getDestination(){
        return destination;
    }

    /**
     * @return Road name
     */
    public String getName(){
        return name;
    }

    /**
     * @return Town start
     */
    public Town getSource(){
        return source;
    }

    /**
     * @return Cost to travel
     */
    public int getWeight(){
        return weight;
    }

    /**
     * @return Name for string representation
     */
    @Override
    public String toString(){
        return name;
    }


}
