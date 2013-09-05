
package comm;

import common.DialogManager;
import enums.DialogType;
import enums.SensorType;
import static enums.SensorType.S_HUMIDITY;
import static enums.SensorType.S_PRESSURE;
import static enums.SensorType.S_RAIN_GAUGE;
import static enums.SensorType.S_TEMPERATURE;
import static enums.SensorType.S_WIND_DIRECTION;
import static enums.SensorType.S_WIND_VELOCITY;
import enums.SerialControlCode;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import java.util.Enumeration;
import java.util.HashMap;

public class ConnectionStrategy implements IStrategy {

    SerialWriter _sWriter;
    SerialReader _sReader;
    
    public ConnectionStrategy(){
        
    }
    @Override
    public void connect(String portName) {
        CommPortIdentifier portIdentifier;
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            CommPort commPort = portIdentifier.open("TigerControlPanel", 2000);

            SerialPort serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(2400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            _sReader = new SerialReader(serialPort.getInputStream());
            serialPort.addEventListener(_sReader);
            serialPort.notifyOnDataAvailable(true);
            _sWriter = new SerialWriter(serialPort.getOutputStream());

        } catch (NoSuchPortException e) {
            DialogManager.showDialog("Error de conexión", "No existe el puerto seleccionado", null, DialogType.ERROR);
        } catch (PortInUseException e) {
            DialogManager.showDialog("Error de conexión", "El puerto seleccionado se encuentra en uso", null, DialogType.ERROR);
        } catch (Exception e) {
            DialogManager.showDialog("Error de conexión", "Se ha producido un error de conexión", null, DialogType.ERROR);
        }
    }

    @Override
    public HashMap<String, CommPortIdentifier> getAvailablePorts() {
        HashMap<String, CommPortIdentifier> portMap = new HashMap<>();
        Enumeration<?> ports = CommPortIdentifier.getPortIdentifiers();

        while (ports.hasMoreElements()) {
            CommPortIdentifier curPort = (CommPortIdentifier) ports
                    .nextElement();

            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portMap.put(curPort.getName(), curPort);
            }
        }
        return portMap;
    }

    @Override
    public void requestSensorData(SensorType s) {
        switch (s) { 
            case S_HUMIDITY:
                _sWriter.sendControlCode(SerialControlCode.HUMIDITY_REQUEST);
                break;
            case S_PRESSURE:
                _sWriter.sendControlCode(SerialControlCode.PRESSURE_REQUEST);
                break;
            case S_RAIN_GAUGE:
                _sWriter.sendControlCode(SerialControlCode.RAINGAUGE_REQUEST);
                break;
            case S_TEMPERATURE:
                _sWriter.sendControlCode(SerialControlCode.TEMPERATURE_REQUEST);
                break;
            case S_WIND_DIRECTION:
                _sWriter.sendControlCode(SerialControlCode.WINDDIRECTION_REQUEST);
                break;
            case S_WIND_VELOCITY:
                _sWriter.sendControlCode(SerialControlCode.WINDVELOCITY_REQUEST);
                break;
        }
    }
    
}
