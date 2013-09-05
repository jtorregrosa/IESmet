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
import java.nio.ByteBuffer;

public class SerialDirector {

    private SerialReader _sReader;
    private SerialWriter _sWriter;
    
    private IStrategy _strategy;
    private static SerialDirector _INSTANCE = new SerialDirector();

    private SerialDirector() {
    }

    public static SerialDirector getInstance() {
        createInstance();
        return _INSTANCE;
    }

    private static void createInstance() {
        if (_INSTANCE == null) {
            synchronized (SerialDirector.class) {
                if (_INSTANCE == null) {
                    _INSTANCE = new SerialDirector();
                }
            }
        }
    }
    
    public void setStrategy(IStrategy s){
        _strategy = s;
    }

    public HashMap<String, CommPortIdentifier> getAvailablePorts() {
        return _strategy.getAvailablePorts();
    }

    public void connect(String portName) {
        _strategy.connect(portName);
    }

    public void requestSensorData(SensorType s) {
       _strategy.requestSensorData(s);
    }

    public void replySerialEvent(ByteBuffer b) {
        double value;
        switch (SerialScheduler.getInstance()._lastRequestType.poll()) {
            case S_HUMIDITY:
                value = SerialInterpreter.interpretHumidity(b);
                SerialData.getInstance().setHumidity(value);
                break;
            case S_PRESSURE:
                value = SerialInterpreter.interpretPressure(b);
                SerialData.getInstance().setPressure(value);
                break;
            case S_RAIN_GAUGE:
                value = SerialInterpreter.interpretRainGauge(b);
                SerialData.getInstance().setRainGauge(value);
                break;
            case S_TEMPERATURE:
                value = SerialInterpreter.interpretTemperature(b);
                SerialData.getInstance().setTemperature(value);
                break;
            case S_WIND_DIRECTION:
                value = SerialInterpreter.interpretWDirection(b);
                SerialData.getInstance().setWindDirection(value);
                break;
            case S_WIND_VELOCITY:
                value = SerialInterpreter.interpretWVelocity(b);
                SerialData.getInstance().setWindVelocity(value);
                break;
        }
        SerialData.getInstance().UpdateCompleted();
    }
}
