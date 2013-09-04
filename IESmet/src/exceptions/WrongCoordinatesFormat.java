package exceptions;

public class WrongCoordinatesFormat extends Exception {
    private static final long serialVersionUID = -9020369289177163360L;

	public WrongCoordinatesFormat(String message) {
        super(message);
    }

    public WrongCoordinatesFormat(String message, Throwable throwable) {
        super(message, throwable);
    }

}