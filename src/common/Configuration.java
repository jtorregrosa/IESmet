package common;

import exceptions.NotValidCoordinates;

public class Configuration {

	private static Configuration singletonObject;
	
	// GUI
	//public static Localization LOCALIZATION;
	
	// SERIAL OPTIONS
	public static int BAUDIOS;
	public static Boolean PARITY;
	public static int NUM_BYTES_WORD;
	public static int NUM_WORDS;
	public static byte INIC_BYTE;
	
	
	private Configuration() {
		/*TODO LEER DESDE FICHERO DE TEXTO LA CONFIGURACIÓN INICIAL*/ 
		/*try {
			LOCALIZATION= new  Localization("España", "Alicante", "San Vicente",38.390576,-0.521283);
		} catch (NotValidCoordinates e) {
			LOCALIZATION = new Localization();
		}*/
		
	}
	public static synchronized Configuration getSingletonObject() {
		if (singletonObject == null) {
			singletonObject = new Configuration();
		}
		return singletonObject;
	}
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
