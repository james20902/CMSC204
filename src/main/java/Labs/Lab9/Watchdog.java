package Labs.Lab9;

public class Watchdog extends Subsystem{

    public static final long WATCHDOG_TIMING = 100;

    //todo reimplement watchdog functionality with fault tolerance
    //because subsystems shouldnt shutdown after one measly error
    public Watchdog(){
        super(WATCHDOG_TIMING, Thread.MAX_PRIORITY);
    }


    @Override
    public boolean init() {
        System.out.println("watchdog thread started!");
        return true;
    }

    @Override
    public boolean loop() {
        System.out.println("hello from the watchdog");
        return true;
    }

    @Override
    public boolean cleanUp() {
        System.out.println("watchdog shutting down...");
        return true;
    }


}
