package builder.queries;

public class OrderBy {
    String command;

    public OrderBy(String column, String operator) {
        this.command = "order by " + column + " " + operator;
    }

    public String getCommand() {
        return command;
    }
}
