package exceptions;

public class SessionIdAlreadyRegisteredException extends Exception {
    public SessionIdAlreadyRegisteredException(String sessionID) {
        super("セッションID (" + sessionID + ") は登録済みです。");
    }
}
