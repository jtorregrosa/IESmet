package exceptions;

public class WrongSerialDataFormat extends Exception {

	public WrongSerialDataFormat(String message) {
        super(message);
    }

    public WrongSerialDataFormat(String message, Throwable throwable) {
        super(message, throwable);
    }

}