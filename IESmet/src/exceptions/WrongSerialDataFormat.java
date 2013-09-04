package exceptions;

public class WrongSerialDataFormat extends Exception{
    private static final long serialVersionUID = 468119870587815891L;

	public WrongSerialDataFormat(String message) {
        super(message);
    }

    public WrongSerialDataFormat(String message, Throwable throwable) {
        super(message, throwable);
    }

}