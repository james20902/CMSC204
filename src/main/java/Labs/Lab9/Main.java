package Labs.Lab9;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("program entrypoint");
        Rocket rocket = new Rocket();
        rocket.registerSubsystems(new Watchdog(), new Telemetry());
        rocket.start();
    }

}
