package enums;

public enum SerialControlCode {
	ALIVE_CONTROL ((byte)0x00),
	VERSION_CONTROL ((byte)0x01),
	
	TEMPERATURE_REQUEST ((byte)0x30),
	PRESSURE_REQUEST ((byte)0x31),
	HUMIDITY_REQUEST ((byte)0x32),
	WINDVELOCITY_REQUEST ((byte)0x33),
	WINDDIRECTION_REQUEST ((byte)0x34),
	RAINGAUGE_REQUEST ((byte)0x35),
	
	SENSORSTATE_CONFIG ((byte)0x50),
	BAUDRATE_CONFIG ((byte)0x51),
	SETBAUDRATE_CONFIG ((byte)0x52);
	
	private final byte hexCode;
	
	SerialControlCode(byte hexCode){
		this.hexCode=hexCode;
	}
	
	public byte getHexCode(){
		return hexCode;
	}
}
