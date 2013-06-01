package comm;

import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class SerialInterpreter {
private static DecimalFormatSymbols _dfs= new DecimalFormatSymbols();

private static DecimalFormat _df1= new DecimalFormat("#.#"); 
    private SerialInterpreter() {

    }

    public static double interpretTemperature(ByteBuffer buffer) {
        _dfs.setDecimalSeparator('.');
        _df1.setRoundingMode(RoundingMode.HALF_UP);
        _df1.setDecimalFormatSymbols(_dfs);
        
        buffer.position(0);
        short value = buffer.getShort();
        System.out.println("Valor : " + value);      
        double dvalue;
        
        if(value<=2047)
            dvalue = 0.0625 * value;
        else
            dvalue = -0.0625 * (value-2048);

        dvalue=Double.parseDouble(_df1.format(dvalue));


        return dvalue;
    }

    public static double interpretPressure(ByteBuffer buffer) {
        buffer.position(0);
        short value = buffer.getShort();
        double dvalue;

        dvalue = value * 1.08613 + 105.5556;

        dvalue=(double)Math.round(dvalue);
        
        return dvalue;
    }

    public static double interpretHumidity(ByteBuffer buffer) {
        buffer.position(0);
        short value = buffer.getShort();
        double dvalue;

        dvalue = (((5.d*(double)value)/1023.d)-0.8)/0.032;
        
        dvalue=(double)Math.round(dvalue);

        return dvalue;
    }

    public static double interpretWVelocity(ByteBuffer buffer) {
        buffer.position(0);
        short value = buffer.getShort();
        double dvalue;

        dvalue = 2.4438 * value;
        dvalue=(double)Math.round(dvalue);
        
        return dvalue;
    }

    public static double interpretWDirection(ByteBuffer buffer) {
        buffer.position(0);
        short value = buffer.getShort();
        double dvalue;
        System.out.println("VALOR VELETA: " + value);

        dvalue = (360*value)/255;
        

        return dvalue;
    }

    public static double interpretRainGauge(ByteBuffer buffer) {
        buffer.position(0);
        short value = buffer.getShort();
        double dvalue;
        
        dvalue = value*4;

        return dvalue;
    }
}
