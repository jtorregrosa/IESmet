package exceptions;

public class WrongCoordinatesFormat extends Exception {

	public WrongCoordinatesFormat(String message) {
        super(message);
    }

    public WrongCoordinatesFormat(String message, Throwable throwable) {
        super(message, throwable);
    }

}