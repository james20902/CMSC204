package Labs.Lab9;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Subsystem implements Runnable{

    private Thread worker;
    private final AtomicBoolean alive = new AtomicBoolean(false);

    private final long delay;
    private final boolean critical;
    private final long cleanUpTimeMilliseconds;
    public final String className = getClass().getSimpleName();

    public Subsystem(long milliseconds){
        this(milliseconds, Thread.NORM_PRIORITY);
    }

    public Subsystem(long milliseconds, int priority){
        this(milliseconds, priority, false, 500);
    }

    public Subsystem(long milliseconds, int priority, boolean critical, long cleanUpTimeMilliseconds){
        this.delay = milliseconds;
        this.critical = critical;
        this.cleanUpTimeMilliseconds = cleanUpTimeMilliseconds;

        worker = new Thread(this);
        worker.setDaemon(!this.critical);
        worker.setPriority(priority);
    }

    public synchronized boolean start(){
        if(worker.isAlive()){
            System.out.println("subsystem is already running: " + className);
        }
        alive.set(true);
        init();
        worker.start();
        return worker.isAlive();
    }

    public synchronized void shutdown(){
        alive.set(false);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(this::cleanUp);
        try {
            if(!future.get(cleanUpTimeMilliseconds, TimeUnit.MILLISECONDS)){
                System.out.println("something went wrong during shutdown execution!");
            }
        } catch (ExecutionException e) {
            System.out.println("something went wrong with execution");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("subsystem was interrupted during shutdown, this should not happen!");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("subsystem timed out!");
            e.printStackTrace();
        }
        executor.shutdownNow();
    }

    public synchronized boolean isAlive(){
        return alive.get();
    }

    public boolean isCritical(){
        return critical;
    }


    @Override
    public void run() {
        while(alive.get()){
            try {
                delayedLoop();
            } catch (InterruptedException e) {
                e.printStackTrace();
                shutdown();
            }
        }
    }

    private void delayedLoop() throws InterruptedException {
        if(!loop()){
            throw new InterruptedException("iteration in loop failed for subsystem: " + className);
        }
        Thread.sleep(delay);
    }

    public abstract boolean init();

    public abstract boolean loop();

    public abstract boolean cleanUp();

}
