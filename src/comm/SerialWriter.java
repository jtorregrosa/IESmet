package comm;

import exceptions.WrongSerialDataFormat;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class SerialWriter implements SerialPortEventListener 
{
    private InputStream Stream;
    //private ByteBuffer Buffer;
    
    public SerialWriter ( InputStream in )
    {
        this.Stream = in;
    }
    
    public void serialEvent(SerialPortEvent arg0) {
    	ByteBuffer Buffer = ByteBuffer.allocate(13);
        int data;
        Buffer.clear();
        try
        {
            while ( ( data = Stream.read()) > -1 )
            {
            	
                if ( data == 10 ) {
                    break;
                }
                Buffer.put((byte) data);
            }
            System.out.println("{"+Buffer.get(0)+","+Buffer.get(1)+","+Buffer.get(2)+","+Buffer.get(3)+","+Buffer.get(4)+","+Buffer.get(5)+"}");
            //System.out.print(new String(Buffer,0,len));
            //SerialInterpreter.interpretTemperature(Buffer,0,len);
            //SerialData.getInstance().setTemperature(Buffer.getShort());
            SerialData.getInstance().UpdateData(Buffer);
        }
        catch ( IOException e )
        {
            e.printStackTrace();
            System.exit(-1);
        } catch (WrongSerialDataFormat e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}             
    }

}
