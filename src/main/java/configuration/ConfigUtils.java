package configuration;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ConfigUtils {

    public static BuildingConfig createBuildingConfig(String configName) throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(configName);
        properties.load(inputStream);
        return new BuildingConfig(properties);
    }

    public static ElevatorConfig createElevatorConfig(String configPath) throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(configPath);
        properties.load(inputStream);
        return new ElevatorConfig(properties);
    }
}
