package by.tsarenkov.shop.dao;
;

public class SaveNewUserException extends Exception{

    public SaveNewUserException() {
        super();
    }

    public SaveNewUserException(String message) {
        super(message);
    }

    public SaveNewUserException(Exception e) {
        super(e);
    }

    public SaveNewUserException(String message, Exception e) {
        super(message, e);
    }
}
