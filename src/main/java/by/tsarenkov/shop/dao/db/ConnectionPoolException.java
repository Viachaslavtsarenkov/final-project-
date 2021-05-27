package by.tsarenkov.shop.dao.db;

public class ConnectionPoolException extends Exception {

    public ConnectionPoolException() {}

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Exception e) {
        super(e);
    }

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
