package configuration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Getter
@Slf4j
public class BuildingConfig {
    private final int numberOfFloors;
    private final int numberOfElevators;
    private final List<ElevatorConfig> elevatorsConfigs = new ArrayList<>();
    private final String DEFAULT_CONFIG = "elevator.properties";

    public BuildingConfig(Properties properties) {
        numberOfFloors = Integer.parseInt(properties.getProperty("numberOfFloors", "9"));
        numberOfElevators = Integer.parseInt(properties.getProperty("numberOfElevators", "2"));
        try {
            String[] configs = properties.getProperty("elevatorsConfigs", DEFAULT_CONFIG).split(";");
            for (int i = 0; i < numberOfElevators; ++i) {
                if (i < configs.length) {
                    elevatorsConfigs.add(ConfigUtils.createElevatorConfig(configs[i]));
                } else {
                    elevatorsConfigs.add(ConfigUtils.createElevatorConfig(DEFAULT_CONFIG));
                }
            }
        } catch (Exception ex) {
            log.error("Config error");
            ex.printStackTrace();
        }
    }

    public BuildingConfig() {
        this(new Properties());
    }
}
