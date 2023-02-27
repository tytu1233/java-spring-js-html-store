package pl.britenet.consoleapp.obj.command;

public abstract class Command {

    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract void execute();

    public String getName() {
        return name;
    }
}
