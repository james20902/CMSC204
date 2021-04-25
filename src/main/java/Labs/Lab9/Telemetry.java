package Labs.Lab9;

import java.util.concurrent.ExecutionException;

public class Telemetry extends Subsystem {

    public Telemetry(){
        super(250);
    }

    @Override
    public void init() {
        System.out.println("telemetry started");
    }

    @Override
    public void loop() {
        System.out.println("telemetry packet sent");
    }

    @Override
    public void cleanUp() {
        try {
            //whoops
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("telemetry subsystem shutting down");
    }
}
