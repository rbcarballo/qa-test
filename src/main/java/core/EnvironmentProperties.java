package core;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class EnvironmentProperties {

    public static String getEnvironmentProperties(String key) {
        return Objects.requireNonNull(loadProperties()).getProperty(key.trim());
    }

    private static Properties loadProperties() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("TextExecutionProperties.properties");
            if (inputStream == null) {
                throw new Exception("Properties file not found ");
            } else {
                Properties properties = new Properties();
                properties.load(inputStream);
                return properties;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
