package Labs.Lab9;

public class Telemetry extends Subsystem {

    public Telemetry(){
        super(250);
    }

    @Override
    public boolean init() {
        System.out.println("telemetry started");
        return true;
    }

    @Override
    public boolean loop() {
        System.out.println("telemetry packet sent");
        return false;
    }

    @Override
    public boolean cleanUp() {
        //remove this trycatch THIS IS JUST FOR WATCHDOG TESTING
        System.out.println("telemetry subsystem shutting down");
        return true;
    }
}
