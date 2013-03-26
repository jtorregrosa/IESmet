package enums;

public enum SerialControlCodes {
	ALIVE_CONTROL (0x00),
	VERSION_CONTROL (0x01),
	
	TEMPERATURE_REQUEST (0x30),
	PRESSURE_REQUEST (0x31),
	HUMIDITY_REQUEST (0x32),
	WINDVELOCITY_REQUEST (0x33),
	WINDDIRECTION_REQUEST (0x34),
	RAINGAUGE_REQUEST (0x35),
	
	SENSORSTATE_CONFIG (0x50),
	BAUDRATE_CONFIG (0x51),
	SETBAUDRATE_CONFIG (0x52);
	
	private final int hexCode;
	
	SerialControlCodes(int hexCode){
		this.hexCode=hexCode;
	}
	
	public int getHexCode(){
		return hexCode;
	}
}
