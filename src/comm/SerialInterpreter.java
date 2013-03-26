package comm;

import java.nio.ByteBuffer;

public class SerialInterpreter {

	private SerialInterpreter() {
	}

	public static double interpretTemperature(ByteBuffer buffer) {
		char firstByte=(char)buffer.get(0);
		//int secondByte= (0x000000FF & ((int)buffer.get(1)));
		System.out.println("Binario:" + Integer.toBinaryString(firstByte));
		short anUnsignedByte = (short) firstByte;
		char anUnsignedShort = 0;
		
		//char value = (char) (firstByte << 8 | secondByte);
		
		//double dvalue;

		/*if (value < 32766)
			dvalue = 0.0625 * value;
		else
			dvalue = -0.0625 * value;*/
		return firstByte;
	}

	public static double interpretPressure(ByteBuffer buffer) {
		short value = buffer.get(1);
		//System.out.println(value);
		double dvalue;

		dvalue = value * 1.08613 + 105.5556;

		return dvalue;
	}

	public static double interpretHumidity(ByteBuffer buffer) {
		short value = buffer.get(2);
		double dvalue;

		dvalue = (204.6 - value * 0.816) / (0.031 * value);

		return dvalue;
	}

	public static double interpretWVelocity(ByteBuffer buffer) {
		short value = buffer.get(3);
		double dvalue;

		dvalue = 2.4438 * value;

		return dvalue;
	}

	public static double interpretWDirection(ByteBuffer buffer) {
		short value = buffer.get(4);
		double dvalue;

		dvalue = 0;

		return dvalue;
	}

	public static double interpretRainGauge(ByteBuffer buffer) {
		short value = buffer.get(5);
		double dvalue;

		dvalue = 0;

		return dvalue;
	}
}
