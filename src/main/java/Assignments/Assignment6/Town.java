package Assignments.Assignment6;

public class Town implements Comparable<Town>{

    String name;

    public Town(String name){
        this.name = name;
    }

    public Town(Town templateTown){
        this(templateTown.name);
    }

    @Override
    public int compareTo(Town o) {
        return name.compareTo(o.getName());
    }

    public boolean equals(Town T){
        return name.equals(T.getName());
    }

    public String getName(){
        return name;
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public String toString(){
        return "";
    }

}
