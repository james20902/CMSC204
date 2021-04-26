package Labs.Lab9;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Rocket extends Subsystem {

    private final Set<Subsystem> subsystems;

    public Rocket(){
        super(20, Thread.NORM_PRIORITY, true, 1000);
        subsystems = new HashSet<>();
    }

    public void registerSubsystems(Subsystem... systems){
        subsystems.addAll(Arrays.asList(systems));
    }

    @Override
    public boolean init() {
        if(subsystems.isEmpty()){
            throw new IllegalThreadStateException("no subsystems present");
        }
        subsystems.forEach(Subsystem::start);
        return true;
    }

    @Override
    public boolean loop() {
        System.out.println("main rocket loop");
        return true;
    }

    //todo, make the requestCleanShutdown call blocking (request future)
    @Override
    public boolean cleanUp() {
        requestCleanShutdown();
        System.out.println("main loop clean shutdown");
        return true;
    }

    private void requestCleanShutdown(){
        //asynchronously request shutdown, this assumes the shutdown method will always complete
        //which it should because nothing in the shutdown method can hitch, cleanUp is already being timed out
        subsystems.stream().filter((Subsystem s) -> !s.isCritical()).forEach((Subsystem s) -> {
            new Thread(s::shutdown).start();
        });
    }
}
