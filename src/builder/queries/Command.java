package builder.queries;

public abstract class Command {
    String command;

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
