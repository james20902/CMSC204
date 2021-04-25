package Labs.Lab9;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Watchdog extends Subsystem{

    public static final long WATCHDOG_TIMING = 100;

    private static Watchdog instance;
    private Set<Subsystem> subsystems;

    public static Watchdog getInstance(){
        if(instance == null){
            instance = new Watchdog();
        }
        return instance;
    }

    private Watchdog(){
        super(WATCHDOG_TIMING, Thread.MAX_PRIORITY);
        subsystems = new HashSet<>();
    }

    public void registerSubsystems(Subsystem... systems){
        subsystems.addAll(Arrays.asList(systems));
    }

    @Override
    public void init() {
        System.out.println("watchdog thread started!");
        if(subsystems.isEmpty()){
            throw new IllegalThreadStateException("no subsystems present");
        }
        for(Subsystem s : subsystems){
            s.start();
        }
    }

    //todo, have watchdog launch subsystem threads and monitor with futures
    @Override
    public void loop() {
        System.out.println("hello from the watchdog");
    }

    @Override
    public void cleanUp() {
        requestCleanShutdown();
        System.out.println("all subsystem threads shutdown, watchdog shutting down...");
    }

    private void requestCleanShutdown(){
        for(Subsystem s : subsystems){
            s.shutdown();
        }
    }
}
