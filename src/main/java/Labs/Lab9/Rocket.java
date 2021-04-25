package Labs.Lab9;

public class Rocket extends Subsystem {

    public Rocket(){
        super(20, Thread.NORM_PRIORITY, true, 5);
    }

    @Override
    public void init() {
        Watchdog.getInstance().registerSubsystems(new Telemetry());
        Watchdog.getInstance().start();
    }

    @Override
    public void loop() {
        System.out.println("main rocket loop");
        if(!Watchdog.getInstance().isAlive()){
            shutdown();
        }
    }

    @Override
    public void cleanUp() {
        System.out.println("main loop clean shutdown");
    }
}
