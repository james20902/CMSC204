package Assignments.Assignment6;

public class Road implements Comparable<Road>{

    Town source, destination;
    int weight;
    String name;

    public Road(Town source, Town destination, int degrees, String name){
        this.source = source;
        this.destination = destination;
        this.weight = degrees;
        this.name = name;
    }

    public Road(Town source, Town destination, String name){
        this(source, destination, 1, name);
    }

    @Override
    public int compareTo(Road o) {
        return name.compareTo(o.getName());
    }

    public boolean contains(Town town){
        return destination.equals(town);
    }

    public boolean equals(Road r){
        return ((source.equals(r.getSource())) &&
                (destination.equals(r.getDestination()))) ||
                ((source.equals(r.getDestination())) &&
                        (destination.equals(r.getSource())));
    }

    public Town getDestination(){
        return destination;
    }

    public String getName(){
        return name;
    }

    public Town getSource(){
        return source;
    }

    public int getWeight(){
        return weight;
    }

    @Override
    public String toString(){
        return "";
    }


}
