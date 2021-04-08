package business.logic.lab2.exceptions;

public class LoginExistsException extends RuntimeException {
    public LoginExistsException(String msg){
        super(msg);
    }
}
