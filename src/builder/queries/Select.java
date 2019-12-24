package builder.queries;

public class Select extends Command {
    public Select(String modelName) {
        super("select * from " + modelName);
    }
}
