package common;

import exceptions.NotValidCoordinates;

public class Configuration {

	private static Configuration singletonObject;
	
        public enum AppMode{
            NO_CONNECTED,
            CONNECTED,
            EMULATED
        }
        
	// GUI
	//public static Localization LOCALIZATION;
	
	// SERIAL OPTIONS
	public static int BAUDIOS;
	public static Boolean PARITY;
	public static int NUM_BYTES_WORD;
	public static int NUM_WORDS;
	public static byte INIC_BYTE;
        
        // APPLICATION MODES
        public AppMode _mode;
	
	
	private Configuration() {

	}
	public static synchronized Configuration getInstance() {
		if (singletonObject == null) {
			singletonObject = new Configuration();
		}
		return singletonObject;
	}
        @Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
        
        public void setAppMode(AppMode mode){
            _mode = mode;
        }
        
        public AppMode getAppMode(){
            return _mode;
        }
}
