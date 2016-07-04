package ch.lloreggia.dingleberry;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private final String ConfigFileName = "config.properties";
    private Properties _properties;

    public Configuration() throws IOException {
        InputStream inputStream = null;
        try {
            _properties = new Properties();

            inputStream = getClass().getClassLoader().getResourceAsStream(ConfigFileName);
            _properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }

    public int getGpioPin() {
        return getIntValue("gpio-pin", 0);
    }

    public int getServerPort() {
        return getIntValue("server-port", 80);
    }

    public String getValue(String key) {
        return _properties.getProperty(key);
    }

    private int getIntValue(String key, int defaultValue) {
        String str = getValue(key);
        int port;

        try {
            port = Integer.parseInt(str);
        } catch (NumberFormatException nex) {
            port = defaultValue;
        }

        return port;
    }

}
