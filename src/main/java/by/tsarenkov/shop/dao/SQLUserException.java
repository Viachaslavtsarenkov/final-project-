package by.tsarenkov.shop.dao;
;

public class SQLUserException extends Exception{

    public SQLUserException() {
        super();
    }

    public SQLUserException(String message) {
        super(message);
    }

    public SQLUserException(Exception e) {
        super(e);
    }

    public SQLUserException(String message, Exception e) {
        super(message, e);
    }
}
