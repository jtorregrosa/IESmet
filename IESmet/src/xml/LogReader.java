package xml;

import comm.SerialDirector;
import comm.SerialDispatcher;
import common.Configuration;
import enums.SensorType;
import static enums.SensorType.S_HUMIDITY;
import static enums.SensorType.S_PRESSURE;
import static enums.SensorType.S_RAIN_GAUGE;
import static enums.SensorType.S_TEMPERATURE;
import static enums.SensorType.S_WIND_DIRECTION;
import static enums.SensorType.S_WIND_VELOCITY;
import enums.SerialControlCode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

public class LogReader {

    public final static String ATTRIBUTE_SENSOR_ID = "id";
    Boolean stopFlag;
    XMLInputFactory2 _xmlif;
    XMLStreamReader2 _xmlrTemp;
    XMLStreamReader2 _xmlrPress;
    XMLStreamReader2 _xmlrHumi;
    XMLStreamReader2 _xmlrWDir;
    XMLStreamReader2 _xmlrWVel;
    XMLStreamReader2 _xmlrRaGa;
    
    ScheduledThreadPoolExecutor _executor;
    ByteBuffer _lastData;
    SensorType _lastSensor;
    XMLStreamReader2 _lastStream;
    private static LogReader _INSTANCE = new LogReader();

    private LogReader() {
        stopFlag = false;
        _xmlif = null;
        try {
            _xmlif = (XMLInputFactory2) XMLInputFactory2.newInstance();
            _xmlif.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.FALSE);
            _xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
            _xmlif.setProperty(XMLInputFactory.IS_COALESCING, Boolean.FALSE);
            _xmlif.configureForSpeed();
        } catch (FactoryConfigurationError | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        
        _executor = new ScheduledThreadPoolExecutor(1);
        
        
    }

    public static LogReader getInstance() {
        createInstance();
        return _INSTANCE;
    }

    private static void createInstance() {
        if (_INSTANCE == null) {
            synchronized (LogReader.class) {
                if (_INSTANCE == null) {
                    _INSTANCE = new LogReader();
                }
            }
        }
    }

    public Map<Integer, String> autoDetectFiles(String path) {
        Map<Integer, String> result = new HashMap<>();

        File folder = new File(path);
        File[] list = folder.listFiles();
        String filename = "";

        for (int i = 0; i < list.length; i++) {

            if (list[i].isFile()) {
                filename = list[i].getName();
                if (filename.endsWith(".ies") || filename.endsWith(".IES")) {
                    // Check file
                    int id = checkFile(list[i].getAbsolutePath());

                    if (id > 0) {
                        try {
                            result.put(id, list[i].getAbsolutePath());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return result;
    }

    private int checkFile(String filename) {
        int result = -1;

        try {
            XMLStreamReader2 xmlr = (XMLStreamReader2) _xmlif.createXMLStreamReader(filename, new FileInputStream(filename));

            int eventType = xmlr.getEventType();
            String curElement = "";

            while (xmlr.hasNext()) {
                eventType = xmlr.next();
                switch (eventType) {
                    case XMLEvent.START_ELEMENT:
                        curElement = xmlr.getName().toString();
                        if (curElement.equalsIgnoreCase("sensor")) {
                            if (xmlr.getAttributeCount() > 0) {
                                result = Integer.parseInt(xmlr.getAttributeValue(null, "id"));
                            }
                        }
                        break;
                    case XMLEvent.CHARACTERS:

                        break;
                    case XMLEvent.END_ELEMENT:

                        break;
                    case XMLEvent.END_DOCUMENT:

                }
            }
            xmlr.closeCompletely();
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void sendControlCode(SerialControlCode code) {
        try {
            _lastData = null;
            String filename;

            switch (code) {
                case TEMPERATURE_REQUEST:
                    filename = Configuration.getInstance().getEmulationPath(SensorType.S_TEMPERATURE);

                    if (_xmlrTemp == null) {
                        _xmlrTemp = (XMLStreamReader2) _xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
                    }
                    _lastSensor = SensorType.S_TEMPERATURE;
                    _lastStream = _xmlrTemp;
                    break;
                case PRESSURE_REQUEST:
                    filename = Configuration.getInstance().getEmulationPath(SensorType.S_PRESSURE);
                    if (_xmlrPress == null) {
                        _xmlrPress = (XMLStreamReader2) _xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
                    }
                   _lastSensor = SensorType.S_PRESSURE;
                    _lastStream = _xmlrPress;
                    break;
                case HUMIDITY_REQUEST:
                    filename = Configuration.getInstance().getEmulationPath(SensorType.S_HUMIDITY);
                    if (_xmlrHumi == null) {
                        _xmlrHumi = (XMLStreamReader2) _xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
                    }
                    _lastSensor = SensorType.S_HUMIDITY;
                    _lastStream = _xmlrHumi;
                    break;
                case WINDVELOCITY_REQUEST:
                    filename = Configuration.getInstance().getEmulationPath(SensorType.S_WIND_VELOCITY);
                    if (_xmlrWVel == null) {
                        _xmlrWVel = (XMLStreamReader2) _xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
                    }
                    _lastSensor = SensorType.S_WIND_VELOCITY;
                    _lastStream = _xmlrWVel;
                    break;
                case WINDDIRECTION_REQUEST:
                    filename = Configuration.getInstance().getEmulationPath(SensorType.S_WIND_DIRECTION);
                    if (_xmlrWDir == null) {
                        _xmlrWDir = (XMLStreamReader2) _xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
                    }
                    _lastSensor = SensorType.S_WIND_DIRECTION;
                    _lastStream = _xmlrWDir;
                    break;
                case RAINGAUGE_REQUEST:
                    filename = Configuration.getInstance().getEmulationPath(SensorType.S_RAIN_GAUGE);
                    if (_xmlrRaGa == null) {
                        _xmlrRaGa = (XMLStreamReader2) _xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
                    }
                    _lastSensor = SensorType.S_RAIN_GAUGE;
                    _lastStream = _xmlrRaGa;
                    break;
            }
            
            _executor.schedule(new Runnable(){
                public void run(){
                  
                    _lastData = readData(_lastSensor, _lastStream);
                    SerialDirector.getInstance().replySerialEvent(_lastData);

                    SerialDispatcher.getInstance().blocked=false;
               }
            }, 100, TimeUnit.MILLISECONDS);
            
            
            
        } catch (Exception ex) {

            ex.printStackTrace();
        }

    }

    private ByteBuffer readData(SensorType sensor, XMLStreamReader2 stream) {
        Boolean reading = false;
        Boolean exit = false;
        ByteBuffer b = ByteBuffer.allocateDirect(2);
        b.clear();
        try {
            int eventType = stream.getEventType();
            String curElement = "";

            while (stream.hasNext() && !exit) {
                eventType = stream.next();
                switch (eventType) {
                    case XMLEvent.START_ELEMENT:
                        curElement = stream.getName().toString();
                        if (curElement.equalsIgnoreCase("value")) {
                            reading = true;
                        }
                        break;
                    case XMLEvent.CHARACTERS:
                        if (reading) {
                            b.putShort((short)Integer.parseInt(stream.getText()));
                            exit = true;
                            reading = false;

                        }
                        break;
                    case XMLEvent.END_ELEMENT:
                        if (curElement.equalsIgnoreCase("value")) {
                            reading = false;
                        }
                        break;
                    case XMLEvent.END_DOCUMENT:
                        stream.close();
                        stream = reopenStream(sensor);
                }
            }
           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }

    private XMLStreamReader2 reopenStream(SensorType sensor) {
        String filename = "";
        XMLStreamReader2 stream = null;
        try {
            switch (sensor) {
                case S_TEMPERATURE:
                    filename = Configuration.getInstance().getEmulationPath(SensorType.S_TEMPERATURE);
                    
                        stream =_xmlrTemp = (XMLStreamReader2) _xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
                    break;
                case S_PRESSURE:
                    filename = Configuration.getInstance().getEmulationPath(SensorType.S_PRESSURE);
                   
                         stream =_xmlrPress = (XMLStreamReader2) _xmlif.createXMLStreamReader(filename, new FileInputStream(filename));

                    break;
                case S_HUMIDITY:
                    filename = Configuration.getInstance().getEmulationPath(SensorType.S_HUMIDITY);
                   
                        stream = _xmlrHumi = (XMLStreamReader2) _xmlif.createXMLStreamReader(filename, new FileInputStream(filename));

                    break;
                case S_WIND_VELOCITY:
                    filename = Configuration.getInstance().getEmulationPath(SensorType.S_WIND_VELOCITY);
                    
                         stream =_xmlrWVel = (XMLStreamReader2) _xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
                    
                    break;
                case S_WIND_DIRECTION:
                    filename = Configuration.getInstance().getEmulationPath(SensorType.S_WIND_DIRECTION);
                    
                         stream =_xmlrWDir = (XMLStreamReader2) _xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
                    
                    break;
                case S_RAIN_GAUGE:
                    filename = Configuration.getInstance().getEmulationPath(SensorType.S_RAIN_GAUGE);
                    
                         stream =_xmlrRaGa = (XMLStreamReader2) _xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
                   
                    break;
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
        return stream;
    }
}
