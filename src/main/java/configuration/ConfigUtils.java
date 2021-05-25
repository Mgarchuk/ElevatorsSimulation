package configuration;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ConfigUtils {

    public static String getArgument(String argument, String def) {

        int position = argument.indexOf("=");
        if (position == -1) {
            return def;
        }

        return argument.substring(position + 1);
    }

    public static BuildingConfig createBuildingConfig(String configName) throws IOException {

        if (configName == null) {
            return new BuildingConfig();
        }

        Properties properties = new Properties();

        try {
            InputStream inputStream = new FileInputStream(configName);
            properties.load(inputStream);
        } catch (FileNotFoundException ex) {
            return new BuildingConfig();
        }

        return new BuildingConfig(properties);
    }

    public static ElevatorConfig createElevatorConfig(String configPath) throws IOException {

        if (configPath == null) {
            return new ElevatorConfig();
        }

        Properties properties = new Properties();

        try {
            InputStream inputStream = new FileInputStream(configPath);
            properties.load(inputStream);
        } catch (FileNotFoundException ex) {
            return new ElevatorConfig();
        }

        return new ElevatorConfig(properties);
    }

    public static GeneratorConfig createGeneratorConfig(String configName) throws IOException {

        if (configName == null) {
            return new GeneratorConfig();
        }

        Properties properties = new Properties();

        try {
            InputStream inputStream = new FileInputStream(configName);
            properties.load(inputStream);
        } catch (FileNotFoundException ex) {
            return new GeneratorConfig();
        }

        return new GeneratorConfig(properties);
    }

    public static String getBuildingConfigName(String[] args) {

        String configName = null;
        for (String arg : args) {
            if (arg.startsWith("--buildingConfig=")) {
                configName = getArgument(arg, null);
            }
        }

        return configName;
    }

    public static String getGeneratorConfigName(String[] args) {

        String configName = null;
        for (String arg : args) {
            if (arg.startsWith("--generatorConfig=")) {
                configName = getArgument(arg, null);
            }
        }

        return configName;
    }
}
