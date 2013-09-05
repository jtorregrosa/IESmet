
package comm;

import enums.SensorType;
import static enums.SensorType.S_HUMIDITY;
import static enums.SensorType.S_PRESSURE;
import static enums.SensorType.S_RAIN_GAUGE;
import static enums.SensorType.S_TEMPERATURE;
import static enums.SensorType.S_WIND_DIRECTION;
import static enums.SensorType.S_WIND_VELOCITY;
import enums.SerialControlCode;
import gnu.io.CommPortIdentifier;
import java.util.HashMap;
import xml.LogReader;

public class EmulationStrategy implements IStrategy{

    public EmulationStrategy(){
 
    }
    
    @Override
    public void connect(String portName) {
        
    }

    @Override
    public HashMap<String, CommPortIdentifier> getAvailablePorts() {
        HashMap<String, CommPortIdentifier> portMap = new HashMap<>();
        return portMap;
    }

    @Override
    public void requestSensorData(SensorType s) {
        switch (s) {
            case S_HUMIDITY:
                LogReader.getInstance().sendControlCode(SerialControlCode.HUMIDITY_REQUEST);
                break;
            case S_PRESSURE:
                LogReader.getInstance().sendControlCode(SerialControlCode.PRESSURE_REQUEST);
                break;
            case S_RAIN_GAUGE:
                LogReader.getInstance().sendControlCode(SerialControlCode.RAINGAUGE_REQUEST);
                break;
            case S_TEMPERATURE:
                LogReader.getInstance().sendControlCode(SerialControlCode.TEMPERATURE_REQUEST);
                break;
            case S_WIND_DIRECTION:
                LogReader.getInstance().sendControlCode(SerialControlCode.WINDDIRECTION_REQUEST);
                break;
            case S_WIND_VELOCITY:
                LogReader.getInstance().sendControlCode(SerialControlCode.WINDVELOCITY_REQUEST);
                break;
        }
    }
    
}
