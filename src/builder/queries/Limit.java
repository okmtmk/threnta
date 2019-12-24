package builder.queries;

public class Limit {
    String command;

    public Limit(long limit) {
        command = "fetch first " + limit + " rows only";
    }

    public String getCommand() {
        return command;
    }
}
