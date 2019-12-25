package builder.queries;

public class Where {
    String command;

    public Where(String column, String operator, long value) {
        command = column + " " + operator + " " + value;
    }

    public Where(String column, String operator, String value) {
        command = column + " " + operator + " '" + value + "'";
    }

    public String getCommand() {
        return command;
    }
}
