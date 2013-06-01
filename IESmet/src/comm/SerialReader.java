package comm;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class SerialReader implements SerialPortEventListener
{
    private InputStream in;

    
    public SerialReader ( InputStream in )
    {
        this.in = in;
    }
    
    @Override
    public void serialEvent(SerialPortEvent arg0) {
        //System.out.println("datos");
    	ByteBuffer buffer = ByteBuffer.allocateDirect(2);
        int data;
        buffer.clear();
        int count=0;
        try
        {
            while ( ( data = in.read()) > -1 && count<2 )
            {
                System.out.println(Integer.toBinaryString(data));
                buffer.put((byte)data);
                count++;
            }
        }
        catch ( IOException e )
        {
            System.out.println("Vaya excepcion");
            System.exit(-1);
        }
        
        SerialDirector.getInstance().replySerialEvent(buffer);
        SerialDispatcher.getInstance().blocked=false;
        //return buffer;
    }

}
