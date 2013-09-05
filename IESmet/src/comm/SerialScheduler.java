/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comm;

import enums.SensorType;
import enums.SerialControlCode;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Jorge
 */
class TempTask implements Runnable {

    @Override
    public void run() {
        SerialScheduler.getInstance()._requestQueue.add(new SerialRequest(SerialControlCode.TEMPERATURE_REQUEST, SerialScheduler.DEFAULT_TEMPERATURE_TIME));
        SerialScheduler.getInstance()._lastRequestType.add(SensorType.S_TEMPERATURE);

    }
}

class HumiTask implements Runnable {

    @Override
    public void run() {
        SerialScheduler.getInstance()._requestQueue.add(new SerialRequest(SerialControlCode.HUMIDITY_REQUEST, SerialScheduler.DEFAULT_HUMIDITY_TIME));
        SerialScheduler.getInstance()._lastRequestType.add(SensorType.S_HUMIDITY);
    }
}

class PressTask implements Runnable {

    @Override
    public void run() {
        SerialScheduler.getInstance()._requestQueue.add(new SerialRequest(SerialControlCode.PRESSURE_REQUEST, SerialScheduler.DEFAULT_PRESSURE_TIME));
        SerialScheduler.getInstance()._lastRequestType.add(SensorType.S_PRESSURE);
    }
}

class WvelTask implements Runnable {

    @Override
    public void run() {
        SerialScheduler.getInstance()._requestQueue.add(new SerialRequest(SerialControlCode.WINDVELOCITY_REQUEST, SerialScheduler.DEFAULT_WVELOCITY_TIME));
        SerialScheduler.getInstance()._lastRequestType.add(SensorType.S_WIND_VELOCITY);

    }
}

class WdirTask implements Runnable {

    @Override
    public void run() {
        SerialScheduler.getInstance()._requestQueue.add(new SerialRequest(SerialControlCode.WINDDIRECTION_REQUEST, SerialScheduler.DEFAULT_WDIRECTION_TIME));
        SerialScheduler.getInstance()._lastRequestType.add(SensorType.S_WIND_DIRECTION);
    }
}

class RgauTask implements Runnable {

    @Override
    public void run() {
        SerialScheduler.getInstance()._requestQueue.add(new SerialRequest(SerialControlCode.RAINGAUGE_REQUEST, SerialScheduler.DEFAULT_RAINGAUGE_TIME));
        SerialScheduler.getInstance()._lastRequestType.add(SensorType.S_RAIN_GAUGE);
    }
}

public class SerialScheduler {

    public final static int DEFAULT_TEMPERATURE_TIME = 1000; // 5 seg
    public final static int DEFAULT_PRESSURE_TIME = 5000; // 0.1 seg
    public final static int DEFAULT_HUMIDITY_TIME = 3000; // 1 min
    public final static int DEFAULT_WVELOCITY_TIME = 1000; // 1 seg
    public final static int DEFAULT_WDIRECTION_TIME = 1000; // 1 seg
    public final static int DEFAULT_RAINGAUGE_TIME = 10000; // 1 seg   
    public Queue<SerialRequest> _requestQueue;
    public Queue<SensorType> _lastRequestType;
    public boolean active;
    private ScheduledThreadPoolExecutor _executor;
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
        active = false;
        _requestQueue = new LinkedList<>();
        _lastRequestType = new LinkedList<>();
        _executor = new ScheduledThreadPoolExecutor(1);

    }

    public void startTasks() {
        _executor.scheduleWithFixedDelay(new TempTask(), 1000, DEFAULT_TEMPERATURE_TIME, TimeUnit.MILLISECONDS);
        _executor.scheduleWithFixedDelay(new PressTask(), 1000, DEFAULT_PRESSURE_TIME, TimeUnit.MILLISECONDS);
        _executor.scheduleWithFixedDelay(new HumiTask(), 1000, DEFAULT_HUMIDITY_TIME, TimeUnit.MILLISECONDS);
        _executor.scheduleWithFixedDelay(new WvelTask(), 1000, DEFAULT_WVELOCITY_TIME, TimeUnit.MILLISECONDS);
        _executor.scheduleWithFixedDelay(new WdirTask(), 1000, DEFAULT_WDIRECTION_TIME, TimeUnit.MILLISECONDS);
        _executor.scheduleWithFixedDelay(new RgauTask(), 1000, DEFAULT_RAINGAUGE_TIME, TimeUnit.MILLISECONDS);
    }
}
