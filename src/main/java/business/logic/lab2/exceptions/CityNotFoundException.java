package business.logic.lab2.exceptions;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String msg) {
        super(msg);
    }
}
