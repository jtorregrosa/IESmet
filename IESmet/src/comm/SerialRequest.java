/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comm;

import enums.SerialControlCode;

/**
 *
 * @author jtorr_000
 */
public class SerialRequest {
    private SerialControlCode _controlCode;
    private long _delay;
    
    public SerialRequest(SerialControlCode controlCode, long delay){
       _controlCode=controlCode;
       _delay=delay;
    }
    
    public SerialControlCode getControlCode(){
        return _controlCode;
    } 
    
    public long getDelay(){
        return _delay;
    }
    
    
}
