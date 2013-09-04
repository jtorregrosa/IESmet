package common;

import exceptions.NotValidCoordinates;

public class Configuration {

    private static Configuration singletonObject;

    public enum AppMode {

        NO_CONNECTED,
        CONNECTED,
        EMULATED
    }

    public enum Sensors {

        TEMPERATURE, PRESSURE, HUMIDITY, WIND_DIRECTION, WIND_VELOCITY, RAIN_GAUGE
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
    // PATHS
    private String _emulationTempfile;
    private String _emulationPresfile;
    private String _emulationHumifile;
    private String _emulationWDirfile;
    private String _emulationWVelfile;
    private String _emulationRaGafile;

    private Configuration() {

        _emulationTempfile = "";

        _emulationPresfile = "";

        _emulationHumifile = "";

        _emulationWDirfile = "";

        _emulationWVelfile = "";

        _emulationRaGafile = "";

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

    public void setAppMode(AppMode mode) {
        _mode = mode;
    }

    public AppMode getAppMode() {
        return _mode;
    }

    public void setEmulationPath(Sensors s, String path) {
        switch (s) {
            case TEMPERATURE:
                _emulationTempfile = path;
                break;
            case PRESSURE:
                _emulationPresfile = path;
                break;
            case HUMIDITY:
                _emulationHumifile = path;
                break;
            case WIND_DIRECTION:
                _emulationWDirfile = path;
                break;
            case WIND_VELOCITY:
                _emulationWVelfile = path;
                break;
            case RAIN_GAUGE:
                _emulationRaGafile = path;
                break;
        }
    }

    public String getEmulationPath(Sensors s) {
        String path = null;
        switch (s) {
            case TEMPERATURE:
                path = _emulationTempfile;
                break;
            case PRESSURE:
                path = _emulationPresfile;
                break;
            case HUMIDITY:
                path = _emulationHumifile;
                break;
            case WIND_DIRECTION:
                path = _emulationWDirfile;
                break;
            case WIND_VELOCITY:
                path = _emulationWVelfile;
                break;
            case RAIN_GAUGE:
                path = _emulationRaGafile;
                break;
        }

        return path;
    }
}
