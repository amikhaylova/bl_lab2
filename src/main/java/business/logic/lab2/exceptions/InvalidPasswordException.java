package business.logic.lab2.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String msg){
        super(msg);
    }
}
