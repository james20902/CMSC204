package Lecture;

public class Circle implements Comparable<Circle>{

    private int radius;

    public Circle(int radius){
        this.radius = radius;
    }

    public int getRadius(){
        return radius;
    }

    @Override
    public int compareTo(Circle o) {
        return (int)-Math.signum(this.getRadius() - o.getRadius());
    }

    @Override
    public String toString(){
        return " radius: " + getRadius();
    }
}
