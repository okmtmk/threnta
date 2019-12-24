package builder.queries;

public class Where extends Command {
    public Where(String column, String operator, long value) {
        super(column + " " + operator + " " + value);
    }

    public Where(String column, String operator, String value) {
        super(column + " " + operator + " '" + value + "'");
    }
}
