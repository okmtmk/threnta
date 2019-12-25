package builder.queries;

public class Offset {
    private String command;

    public Offset(long offset) {
        command = "offset " + offset + " rows";
    }

    public String getCommand() {
        return command;
    }
}
