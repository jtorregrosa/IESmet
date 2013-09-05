/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comm;

import enums.SensorType;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


class ActivateTempTask implements Runnable {

    @Override
    public void run() {
        SerialDispatcher.getInstance().tempEnabled=true;
    }
}

class ActivatePressTask implements Runnable {

    @Override
    public void run() {
        SerialDispatcher.getInstance().pressEnabled=true;
    }
}

class ActivateHumiTask implements Runnable {

    @Override
    public void run() {
        SerialDispatcher.getInstance().humiEnabled=true;
    }
}

class ActivateWVelTask implements Runnable {

    @Override
    public void run() {
        SerialDispatcher.getInstance().wvelEnabled=true;
    }
}

class ActivateWDirTask implements Runnable {

    @Override
    public void run() {
        SerialDispatcher.getInstance().wdirEnabled=true;
    }
}

class ActivateRainTask implements Runnable {

    @Override
    public void run() {
        SerialDispatcher.getInstance().rainEnabled=true;
    }
}

class RequestTask implements Runnable {

    @Override
    public void run() {
        SerialRequest s = SerialScheduler.getInstance()._requestQueue.peek();
        if (s != null && !SerialDispatcher.getInstance().blocked) {
            switch (s.getControlCode()) {
                case TEMPERATURE_REQUEST:
                    if (SerialDispatcher.getInstance().tempEnabled) {
   
                        SerialDirector.getInstance().requestSensorData(SensorType.S_TEMPERATURE);
                        SerialDispatcher.getInstance().tempEnabled=false;

                        SerialDispatcher.getInstance().blocked=true;
                        SerialDispatcher.getInstance()._activator.schedule(new ActivateTempTask(), s.getDelay(), TimeUnit.MILLISECONDS);
                        
                        SerialScheduler.getInstance()._requestQueue.poll();
                    }
                    break;
                case PRESSURE_REQUEST:
                     if (SerialDispatcher.getInstance().pressEnabled) {
                        SerialDirector.getInstance().requestSensorData(SensorType.S_PRESSURE);
                        SerialDispatcher.getInstance().pressEnabled=false;

                         SerialDispatcher.getInstance().blocked=true;
                        SerialDispatcher.getInstance()._activator.schedule(new ActivatePressTask(), s.getDelay(), TimeUnit.MILLISECONDS);
                        
                        SerialScheduler.getInstance()._requestQueue.poll();
                    }
                    break;
                case HUMIDITY_REQUEST:
                     if (SerialDispatcher.getInstance().humiEnabled) {
                        SerialDirector.getInstance().requestSensorData(SensorType.S_HUMIDITY);
                        SerialDispatcher.getInstance().humiEnabled=false;

                         SerialDispatcher.getInstance().blocked=true;
                        SerialDispatcher.getInstance()._activator.schedule(new ActivateHumiTask(), s.getDelay(), TimeUnit.MILLISECONDS);
                        
                        SerialScheduler.getInstance()._requestQueue.poll();
                    }
                    break;
                    
                    case WINDDIRECTION_REQUEST:
                     if (SerialDispatcher.getInstance().wdirEnabled) {
                        SerialDirector.getInstance().requestSensorData(SensorType.S_WIND_DIRECTION);
                        SerialDispatcher.getInstance().wdirEnabled=false;

                         SerialDispatcher.getInstance().blocked=true;
                        SerialDispatcher.getInstance()._activator.schedule(new ActivateWDirTask(), s.getDelay(), TimeUnit.MILLISECONDS);
                        
                        SerialScheduler.getInstance()._requestQueue.poll();
                    }
                    break;
                        
                        case WINDVELOCITY_REQUEST:
                     if (SerialDispatcher.getInstance().wvelEnabled) {
                        SerialDirector.getInstance().requestSensorData(SensorType.S_WIND_VELOCITY);
                        SerialDispatcher.getInstance().wvelEnabled=false;

                         SerialDispatcher.getInstance().blocked=true;
                        SerialDispatcher.getInstance()._activator.schedule(new ActivateWVelTask(), s.getDelay(), TimeUnit.MILLISECONDS);
                        
                        SerialScheduler.getInstance()._requestQueue.poll();
                    }
                    break;
                            
                            case RAINGAUGE_REQUEST:
                     if (SerialDispatcher.getInstance().rainEnabled) {
                        SerialDirector.getInstance().requestSensorData(SensorType.S_RAIN_GAUGE);
                        SerialDispatcher.getInstance().rainEnabled=false;

                         SerialDispatcher.getInstance().blocked=true;
                        SerialDispatcher.getInstance()._activator.schedule(new ActivateRainTask(), s.getDelay(), TimeUnit.MILLISECONDS);
                        
                        SerialScheduler.getInstance()._requestQueue.poll();
                    }
                    break;
            }
        }
    }
}


public class SerialDispatcher {

    private static SerialDispatcher _INSTANCE = new SerialDispatcher();
    public ScheduledThreadPoolExecutor _executor;
    public ScheduledThreadPoolExecutor _activator;
    
    public boolean blocked = false;
    public boolean tempEnabled = true;
    public boolean pressEnabled = true;
    public boolean humiEnabled = true;
    public boolean wvelEnabled = true;
    public boolean wdirEnabled = true;
    public boolean rainEnabled = true;
    
    

    public static SerialDispatcher getInstance() {
        createInstance();
        return _INSTANCE;
    }

    private static void createInstance() {
        if (_INSTANCE == null) {
            synchronized (SerialDispatcher.class) {
                if (_INSTANCE == null) {
                    _INSTANCE = new SerialDispatcher();
                }
            }
        }
    }

    SerialDispatcher() {
        _executor = new ScheduledThreadPoolExecutor(1);
        _activator = new ScheduledThreadPoolExecutor(1);
    }

    public void startTasks() {
        _executor.scheduleWithFixedDelay(new RequestTask(), 5000, 20, TimeUnit.MILLISECONDS);
    }

}
