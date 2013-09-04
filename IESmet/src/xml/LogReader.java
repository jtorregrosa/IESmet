package xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

public class LogReader {

    public final static String ATTRIBUTE_SENSOR_ID = "id";

    public LogReader() {
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

            XMLInputFactory2 xmlif = null;
            try {
                xmlif = (XMLInputFactory2) XMLInputFactory2.newInstance();
                xmlif.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.FALSE);
                xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
                xmlif.setProperty(XMLInputFactory.IS_COALESCING, Boolean.FALSE);
                xmlif.configureForSpeed();
            } catch (FactoryConfigurationError | IllegalArgumentException ex) {
                ex.printStackTrace();
            }


            XMLStreamReader2 xmlr = (XMLStreamReader2) xmlif.createXMLStreamReader(filename, new FileInputStream(filename));

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
                        if (curElement.equals("bigelement")) {
                        }
                        break;
                    case XMLEvent.END_ELEMENT:
                        if (curElement.equals("bigelement")) {
                        }
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
}
