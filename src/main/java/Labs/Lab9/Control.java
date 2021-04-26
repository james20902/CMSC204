package Labs.Lab9;

public class Control extends Subsystem{

    public Control(long milliseconds) {
        super(milliseconds);
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public boolean loop() {
        return true;
    }

    @Override
    public boolean cleanUp() {
        return true;
    }
}
