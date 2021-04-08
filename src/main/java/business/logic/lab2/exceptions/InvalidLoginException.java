package business.logic.lab2.exceptions;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String msg){
        super(msg);
    }
}
