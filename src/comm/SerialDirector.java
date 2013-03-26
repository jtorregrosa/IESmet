package comm;

import enums.DialogType;
import enums.SerialControlCodes;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;

import common.DialogManager;

public class SerialDirector {

	public HashMap<String, CommPortIdentifier> getAvailablePorts() {
		HashMap<String, CommPortIdentifier> portMap = new HashMap<String, CommPortIdentifier>();
		Enumeration<?> ports = CommPortIdentifier.getPortIdentifiers();

		while (ports.hasMoreElements()) {
			CommPortIdentifier curPort = (CommPortIdentifier) ports
					.nextElement();

			if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
				portMap.put(curPort.getName(), curPort);
		}
		return portMap;
	}

	public void connect(String portName) {
		CommPortIdentifier portIdentifier;
		try {
			portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
			CommPort commPort = portIdentifier.open("TigerControlPanel", 2000);

			SerialPort serialPort = (SerialPort) commPort;
			serialPort.setSerialPortParams(2400, SerialPort.DATABITS_8,	SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			InputStream in = serialPort.getInputStream();

			serialPort.addEventListener(new SerialReader(in));
			serialPort.notifyOnDataAvailable(true);

		} catch (NoSuchPortException e) {
			DialogManager.showDialog("Error de conexi�n", "No existe el puerto seleccionado", null, DialogType.ERROR);
		} catch (PortInUseException e) {
			DialogManager.showDialog("Error de conexi�n", "El puerto seleccionado se encuentra en uso", null, DialogType.ERROR);
		} catch (Exception e) {
			DialogManager.showDialog("Error de conexi�n", "Se ha producido un error de conexi�n", null, DialogType.ERROR);
		}

	}
}
