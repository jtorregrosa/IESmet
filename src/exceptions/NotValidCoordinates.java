package exceptions;

public class NotValidCoordinates extends Exception {

	public NotValidCoordinates(String message) {
        super(message);
    }

    public NotValidCoordinates(String message, Throwable throwable) {
        super(message, throwable);
    }

}