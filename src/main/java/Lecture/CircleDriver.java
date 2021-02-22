package Lecture;

import java.util.PriorityQueue;

public class CircleDriver {

    public static void main(String[] args){
        System.out.println("hello");
        Circle a, b, c, d, e;
        PriorityQueue<Circle> queue = new PriorityQueue<>();
        a = new Circle(2);
        b = new Circle(1);
        c = new Circle(4);
        d = new Circle(4);
        e = new Circle(6);

        queue.add(a);
        System.out.println(queue);
        queue.add(b);
        System.out.println(queue);
        queue.add(c);
        System.out.println(queue);
        queue.add(d);
        System.out.println(queue);
        queue.add(e);
        System.out.println(queue);

        System.out.println("\n");
        queue.remove();
        System.out.println(queue);

    }

}
