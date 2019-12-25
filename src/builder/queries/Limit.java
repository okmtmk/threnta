package builder.queries;

public class Limit {
    private String command;

    public Limit(long limit) {
        command = "fetch first " + limit + " rows only";
    }

    public String getCommand() {
        return command;
    }
}