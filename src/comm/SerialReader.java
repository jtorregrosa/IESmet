package comm;

import exceptions.WrongSerialDataFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class SerialReader
{
    private InputStream in;

    
    public SerialReader ( InputStream in )
    {
        this.in = in;
    }
    
    public ByteBuffer obtainReqSensorData() {
    	ByteBuffer buffer = ByteBuffer.allocateDirect(2);
        int data;
        buffer.clear();
        int count=0;
        try
        {
            while ( ( data = in.read()) > -1 && count<2 )
            {
                if ( data == 10 ) {
                    //break;
                }
                buffer.put((byte)data);
                //buffer.put((byte)data2);
                //buffer.put(tempBuffer[1]);
                        //put((byte) data);
                count++;
                //System.out.println("BYTE " + count + ": "+ data +"    " +Integer.toBinaryString(data));
                //System.out.println("BYTE 2" + ": "+ data2 +"    " +Integer.toBinaryString(data2));
            }
            //System.out.println("--------------------------");
        }
        catch ( IOException e )
        {
            System.out.println("Vaya excepcion");
            System.exit(-1);
        }
        
        return buffer;
    }

}
