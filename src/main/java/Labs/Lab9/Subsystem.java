package Labs.Lab9;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Subsystem implements Runnable{

    private Thread worker;
    private final AtomicBoolean alive = new AtomicBoolean(false);
    private final ExecutorService executor;

    private long delay;
    private int priority;
    private final long cleanUpTimeMilliseconds;

    public Subsystem(long milliseconds){
        this(milliseconds, Thread.NORM_PRIORITY);
    }

    public Subsystem(long milliseconds, int priority){
        this(milliseconds, priority, false, 5);
    }

    public Subsystem(long milliseconds, int priority, boolean critical, long cleanUpTimeMilliseconds){
        this.delay = milliseconds;
        this.priority = priority;
        this.cleanUpTimeMilliseconds = cleanUpTimeMilliseconds;

        executor = Executors.newSingleThreadExecutor();
        worker = new Thread(this);
        worker.setDaemon(!critical);
        worker.setPriority(this.priority);

    }

    //todo, do i want to have the worker be managed by an executor or just have the executor for start/stop timeout
    public synchronized void start(){
        alive.set(true);
        init();
        executor.submit(worker);
    }

    public synchronized void shutdown(){
        alive.set(false);
        try {
            if(executor.awaitTermination(cleanUpTimeMilliseconds, TimeUnit.MILLISECONDS)){
                executor.shutdown();
            } else {
                System.out.println("something went wrong in the shutdown request for subsystem: " + getClass().getSimpleName());
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized boolean isAlive(){
        return alive.get();
    }


    @Override
    public void run() {
        while(alive.get()){
            try {
                delayedLoop();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void delayedLoop() throws InterruptedException {
        loop();
        Thread.sleep(delay);
    }

    public abstract void init();

    public abstract void loop();

    public abstract void cleanUp();

}
