/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comm;

import enums.SensorType;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Jorge
 */
class TempTask implements Runnable {

    @Override
    public void run() {
        double value = SerialDirector.getInstance().requestSensorData(SensorType.S_TEMPERATURE);
        System.out.println(value);
        SerialData.getInstance().setTemperature(value);
        SerialData.getInstance().UpdateCompleted();
    }
}

class HumiTask implements Runnable {

    @Override
    public void run() {

        double value = SerialDirector.getInstance().requestSensorData(SensorType.S_HUMIDITY);
        SerialData.getInstance().setHumidity(value);
        SerialData.getInstance().UpdateCompleted();
    }
}

class PressTask implements Runnable {

    @Override
    public void run() {
        double value = SerialDirector.getInstance().requestSensorData(SensorType.S_PRESSURE);
        SerialData.getInstance().setPressure(value);
        SerialData.getInstance().UpdateCompleted();
    }
}

class WvelTask implements Runnable {

    @Override
    public void run() {
        double value = SerialDirector.getInstance().requestSensorData(SensorType.S_WIND_VELOCITY);
        SerialData.getInstance().setWindVelocity(value);
        SerialData.getInstance().UpdateCompleted();
    }
}

class WdirTask implements Runnable {

    @Override
    public void run() {
        double value = SerialDirector.getInstance().requestSensorData(SensorType.S_WIND_DIRECTION);
        SerialData.getInstance().setWindDirection(value);
        SerialData.getInstance().UpdateCompleted();
    }
}

class RgauTask implements Runnable {

    @Override
    public void run() {
        double value = SerialDirector.getInstance().requestSensorData(SensorType.S_RAIN_GAUGE);
        SerialData.getInstance().setRainGauge(value);
        SerialData.getInstance().UpdateCompleted();
    }
}

public class SerialScheduler {

    public final static int DEFAULT_TEMPERATURE_TIME = 5000; // 5 seg
    public final static int DEFAULT_PRESSURE_TIME = 100; // 0.1 seg
    public final static int DEFAULT_HUMIDITY_TIME = 60000; // 1 min
    public final static int DEFAULT_WVELOCITY_TIME = 50; // 1 seg
    public final static int DEFAULT_WDIRECTION_TIME = 1000; // 1 seg
    public final static int DEFAULT_RAINGAUGE_TIME = 100; // 1 seg   
    
    ScheduledThreadPoolExecutor _executor;
    
    private static SerialScheduler _INSTANCE = new SerialScheduler();

    public static SerialScheduler getInstance() {
        createInstance();
        return _INSTANCE;
    }

    private static void createInstance() {
        if (_INSTANCE == null) {
            synchronized (SerialScheduler.class) {
                if (_INSTANCE == null) {
                    _INSTANCE = new SerialScheduler();
                }
            }
        }
    }

    SerialScheduler() {
        _executor = new ScheduledThreadPoolExecutor(1);
    }
    
    public void startTasks(){
        _executor.scheduleWithFixedDelay(new TempTask(), 1, DEFAULT_TEMPERATURE_TIME,TimeUnit.MILLISECONDS);
        _executor.scheduleWithFixedDelay(new PressTask(), 1, DEFAULT_PRESSURE_TIME,TimeUnit.MILLISECONDS);
        _executor.scheduleWithFixedDelay(new HumiTask(), 1, DEFAULT_HUMIDITY_TIME,TimeUnit.MILLISECONDS);
        _executor.scheduleWithFixedDelay(new WvelTask(), 1, DEFAULT_WVELOCITY_TIME,TimeUnit.MILLISECONDS);
        _executor.scheduleWithFixedDelay(new WdirTask(), 1, DEFAULT_WDIRECTION_TIME,TimeUnit.MILLISECONDS);
        _executor.scheduleWithFixedDelay(new RgauTask(), 1, DEFAULT_RAINGAUGE_TIME,TimeUnit.MILLISECONDS);  
    }
}
