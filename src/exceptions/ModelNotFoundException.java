package exceptions;

public class ModelNotFoundException extends Exception {
    public ModelNotFoundException(String modelName, long id) {
        super("Model name : " + modelName + ", id : " + id);
    }
}
