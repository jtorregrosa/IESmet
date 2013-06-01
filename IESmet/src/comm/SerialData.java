package comm;

import java.nio.ByteBuffer;
import java.util.Observable;

import exceptions.WrongSerialDataFormat;

public class SerialData extends Observable {

	private static final SerialData instance = new SerialData();

	private double Temperature;
	private double Humidity;
	private double Pressure;
	private double WindVelocity;
	private double WindDirection;
	private double RainGauge;

	/**
	 * CTOR: Initialize values of members
	 */
	private SerialData() {
		// Initialize values
		Temperature = 0.0;
		Humidity = 0.0;
		Pressure = 0.0;
		WindVelocity = 0.0;
		WindDirection = 0.0;
		RainGauge = 0.0;
	}

	/**
	 * Get the instance of the singleton class SerialData
	 * @return The instance of the singleton.
	 */
	public static SerialData getInstance() {
		return instance;
	}

	/**
	 * Change the state of the observable object and notify the observers that the object was updated.
	 */
	public void UpdateCompleted() {
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Updates the value of the members in the class and call UpdateCompleted()
	 * @param buffer Bytes Buffer that contains the six value
	 * @throws WrongSerialDataFormat 
	 */
	public void UpdateData(ByteBuffer buffer) throws WrongSerialDataFormat {
		
		if(buffer.capacity() >= 13 && buffer.limit()>=13){
			Temperature = SerialInterpreter.interpretTemperature(buffer);
			Humidity = SerialInterpreter.interpretHumidity(buffer);
			Pressure = SerialInterpreter.interpretPressure(buffer);
			WindVelocity = SerialInterpreter.interpretWVelocity(buffer);
			WindDirection = SerialInterpreter.interpretWDirection(buffer);
			RainGauge = SerialInterpreter.interpretRainGauge(buffer);
			//System.out.println("{"+Temperature+","+Humidity+","+Pressure+","+WindVelocity+","+WindDirection+","+RainGauge+"}");
            //System.out.print(new String(Buffer,0,len));
			UpdateCompleted();
		}else
			throw new WrongSerialDataFormat("El buffer de datos debe de ser mínimo de 6 bytes");
	}

	public double getTemperature() {
		return Temperature;
	}

	public void setTemperature(double temperature) {
		Temperature = temperature;
	}

	public double getHumidity() {
		return Humidity;
	}

	public void setHumidity(double humidity) {
		Humidity = humidity;
	}

	public double getPressure() {
		return Pressure;
	}

	public void setPressure(double pression) {
		Pressure = pression;
	}

	public double getWindVelocity() {
		return WindVelocity;
	}

	public void setWindVelocity(double windVelocity) {
		WindVelocity = windVelocity;
	}

	public double getWindDirection() {
		return WindDirection;
	}

	public void setWindDirection(double windDirection) {
		WindDirection = windDirection;
	}

	public double getRainGauge() {
		return RainGauge;
	}

	public void setRainGauge(double rainGauge) {
		RainGauge = rainGauge;
	}

}
