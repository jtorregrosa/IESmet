package comm;

import java.nio.ByteBuffer;

public class SerialInterpreter {

    private SerialInterpreter() {
    }

    public static double interpretTemperature(ByteBuffer buffer) {
        buffer.position(0);
        short value = buffer.getShort();
        //System.out.println(value);      
        double dvalue;
        
        if(value<=2047)
            dvalue = 0.0625 * value;
        else
            dvalue = -0.0625 * (value-2048);
        
        //System.out.println("VALOR: " + value);
        //System.out.println("VALOR INTERPRETADO: " + dvalue);
        return dvalue;
    }

    public static double interpretPressure(ByteBuffer buffer) {
        buffer.position(0);
        short value = buffer.getShort();
        double dvalue;

        dvalue = value * 1.08613 + 105.5556;

        return dvalue;
    }

    public static double interpretHumidity(ByteBuffer buffer) {
        buffer.position(0);
        short value = buffer.getShort();
        double dvalue;

        dvalue = (204.6 - value * 0.816) / (0.031 * value);

        return dvalue;
    }

    public static double interpretWVelocity(ByteBuffer buffer) {
        buffer.position(0);
        short value = buffer.getShort();
        double dvalue;

        dvalue = 2.4438 * value;

        return dvalue;
    }

    public static double interpretWDirection(ByteBuffer buffer) {
        buffer.position(0);
        short value = buffer.getShort();
        double dvalue;

        dvalue = 0;

        return dvalue;
    }

    public static double interpretRainGauge(ByteBuffer buffer) {
        buffer.position(0);
        short value = buffer.getShort();
        double dvalue;

        dvalue = 0;

        return dvalue;
    }
}
