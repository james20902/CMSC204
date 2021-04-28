package Labs.Lab9;

import java.util.LinkedList;
import java.util.Queue;

public class CarQueue{

    Queue<Integer> queue = new LinkedList<>();

    public CarQueue(){
        for(int i = 0; i < 6; i++){
            queue.add((int)(Math.random() * 3));
        }
    }

    public void addToQueue(){
        class QueueRun implements Runnable{
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()){
                    try {
                        queue.add((int)(Math.random() * 3));
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        new Thread(new QueueRun()).start();
    }

    public int deleteQueue(){
        return queue.remove();
    }

}
