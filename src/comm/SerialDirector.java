package comm;

import enums.DialogType;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.util.Enumeration;
import java.util.HashMap;

import enums.SensorType;
import enums.SerialControlCode;
import common.DialogManager;
import static enums.SensorType.S_HUMIDITY;

public class SerialDirector {
    
    private SerialReader _sReader;
    private SerialWriter _sWriter;
    private static SerialDirector _INSTANCE = new SerialDirector();
 
    private SerialDirector() {}
 
    public static SerialDirector getInstance() {
        createInstance();
        return _INSTANCE;
    }
    private static void createInstance() {
        if (_INSTANCE == null) {
            synchronized(SerialDirector.class) {
                if (_INSTANCE == null) { 
                    _INSTANCE = new SerialDirector();
                }
            }
        }
    }
    
    public HashMap<String, CommPortIdentifier> getAvailablePorts() {
        HashMap<String, CommPortIdentifier> portMap = new HashMap<String, CommPortIdentifier>();
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

    public void connect(String portName) {
        CommPortIdentifier portIdentifier;
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            CommPort commPort = portIdentifier.open("TigerControlPanel", 2000);

            SerialPort serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(2400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            _sReader = new SerialReader(serialPort.getInputStream());
            _sWriter = new SerialWriter(serialPort.getOutputStream());

        } catch (NoSuchPortException e) {
            DialogManager.showDialog("Error de conexión", "No existe el puerto seleccionado", null, DialogType.ERROR);
        } catch (PortInUseException e) {
            DialogManager.showDialog("Error de conexión", "El puerto seleccionado se encuentra en uso", null, DialogType.ERROR);
        } catch (Exception e) {
            DialogManager.showDialog("Error de conexión", "Se ha producido un error de conexión", null, DialogType.ERROR);
        }
    }

    public double requestSensorData(SensorType s) {
        double value = Float.POSITIVE_INFINITY;
        switch (s) {
            case S_HUMIDITY:
                _sWriter.sendControlCode(SerialControlCode.HUMIDITY_REQUEST);
                value = SerialInterpreter.interpretHumidity(_sReader.obtainReqSensorData());
                break;
            case S_PRESSURE:
                _sWriter.sendControlCode(SerialControlCode.PRESSURE_REQUEST);
                value = SerialInterpreter.interpretPressure(_sReader.obtainReqSensorData());
                break;
            case S_RAIN_GAUGE:
                _sWriter.sendControlCode(SerialControlCode.RAINGAUGE_REQUEST);
                value = SerialInterpreter.interpretRainGauge(_sReader.obtainReqSensorData());
                break;
            case S_TEMPERATURE:
                _sWriter.sendControlCode(SerialControlCode.TEMPERATURE_REQUEST);
                value = SerialInterpreter.interpretTemperature(_sReader.obtainReqSensorData());
                break;
            case S_WIND_DIRECTION:
                _sWriter.sendControlCode(SerialControlCode.WINDDIRECTION_REQUEST);
                value = SerialInterpreter.interpretWDirection(_sReader.obtainReqSensorData());
                break;
            case S_WIND_VELOCITY:
                _sWriter.sendControlCode(SerialControlCode.WINDVELOCITY_REQUEST);
                value = SerialInterpreter.interpretWVelocity(_sReader.obtainReqSensorData());
                break;
        }

        return value;
    }
}
