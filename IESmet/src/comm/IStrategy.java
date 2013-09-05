package comm;

import enums.SensorType;
import gnu.io.CommPortIdentifier;
import java.util.HashMap;

public interface IStrategy {
    public void connect(String portName);

    public HashMap<String, CommPortIdentifier> getAvailablePorts();

    public void requestSensorData(SensorType s);
}
