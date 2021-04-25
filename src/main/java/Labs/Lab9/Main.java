package Labs.Lab9;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("program entrypoint");
        new Rocket().start();
        Thread.sleep(6000);
        System.out.println("6 seconds past, requesting shutdown");
        Watchdog.getInstance().shutdown();
    }

}
