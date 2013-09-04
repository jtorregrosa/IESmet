package comm;

import java.io.OutputStream;
import enums.SerialControlCode;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialWriter
{
    private OutputStream out;
    
    public SerialWriter ( OutputStream out )
    {
        this.out = out;
    }
    
    public void sendControlCode(SerialControlCode s) {
        try {
            out.write(s.getHexCode());
            System.out.println("Envio ControlCode");
        } catch (IOException ex) {
            Logger.getLogger(SerialWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
