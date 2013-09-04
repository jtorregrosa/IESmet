package exceptions;

public class NotValidCoordinates extends Exception {
    private static final long serialVersionUID = -2218952877233475646L;

	public NotValidCoordinates(String message) {
        super(message);
    }

    public NotValidCoordinates(String message, Throwable throwable) {
        super(message, throwable);
    }

}