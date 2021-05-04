package Assignments.Assignment6;

/**
 * Town data structure for graph vertex representation
 *
 * @author James Pham
 */
public class Town implements Comparable<Town>{

    String name;
    boolean visited;

    /**
     * Vertex constructor
     * @param name name of the town
     */
    public Town(String name){
        this.name = name;
    }

    /**
     * Copy constructor
     * @param templateTown town to copy
     */
    public Town(Town templateTown){
        this(templateTown.name);
    }

    /**
     * Comparison method for iteration
     * @param o town to compare
     * @return 0 if matching, other if not
     */
    @Override
    public int compareTo(Town o) {
        return name.compareTo(o.getName());
    }

    /**
     * Equality check
     * @param o town to compare
     * @return whether or not two towns are equal by name
     */
    @Override
    public boolean equals(Object o){
        Town t = (Town)o;
        return name.equals(t.getName());
    }

    /**
     * @return Name of town
     */
    public String getName(){
        return name;
    }

    /**
     * @return Hashcode of town by name
     */
    @Override
    public int hashCode(){
        return name.hashCode();
    }

    /**
     * @return Town name for string representation
     */
    @Override
    public String toString(){
        return name;
    }

}
