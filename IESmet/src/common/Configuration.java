package common;

import enums.SensorType;

public class Configuration {

    private static Configuration singletonObject;

    public enum AppMode {

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

    public void setEmulationPath(SensorType s, String path) {
        switch (s) {
            case S_TEMPERATURE:
                _emulationTempfile = path;
                break;
            case S_PRESSURE:
                _emulationPresfile = path;
                break;
            case S_HUMIDITY:
                _emulationHumifile = path;
                break;
            case S_WIND_DIRECTION:
                _emulationWDirfile = path;
                break;
            case S_WIND_VELOCITY:
                _emulationWVelfile = path;
                break;
            case S_RAIN_GAUGE:
                _emulationRaGafile = path;
                break;
        }
    }

    public String getEmulationPath(SensorType s) {
        String path = null;
        switch (s) {
            case S_TEMPERATURE:
                path = _emulationTempfile;
                break;
            case S_PRESSURE:
                path = _emulationPresfile;
                break;
            case S_HUMIDITY:
                path = _emulationHumifile;
                break;
            case S_WIND_DIRECTION:
                path = _emulationWDirfile;
                break;
            case S_WIND_VELOCITY:
                path = _emulationWVelfile;
                break;
            case S_RAIN_GAUGE:
                path = _emulationRaGafile;
                break;
        }

        return path;
    }
}
