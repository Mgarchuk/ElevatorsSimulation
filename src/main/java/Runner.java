import models.Elevator;
import services.ElevatorService;
import services.UserSpawnService;
import threadTasks.UsersSpawnTask;
import configuration.BuildingConfig;
import configuration.ConfigUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Building;

import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;

@Getter
@RequiredArgsConstructor
@Slf4j
public class Runner {

    private static final Timer humanAddingTimer = new Timer();
    private static final Random random = new Random();
    private static final String DEFAULT_CONFIG = "building.properties";

    private static String getArgument(String argument, String def) {
        int position = argument.indexOf("="); /// --buildingConfig=building.properties
        if (position == -1) {
            return def;
        }

        return argument.substring(position + 1);
    }

    public static Properties getProperties(String path) {
        Properties properties = new Properties();
        InputStream inputStream = ConfigUtils.class.getResourceAsStream(path);
        if (inputStream == null) {
            log.warn("Can't find config file for building");
            return null;
        }
        try {
            properties.load(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return properties;
    }

    public static void main(String[] args) {
        String configName = DEFAULT_CONFIG;
        for (int i = 0; i < args.length; ++i) {
            if (args[i].startsWith("--buildingConfig=")) {
                configName = getArgument(args[i], DEFAULT_CONFIG);
            }
        }

        BuildingConfig config = null;
        try {
            config = ConfigUtils.createBuildingConfig(configName);
        } catch (Exception ex) {
            log.error("Config error");
            ex.printStackTrace();
        }
        if (config == null) {
            log.error("Can't find config");
            return;
        }

        final Building building = new Building(config);
        final UserSpawnService userSpawnService = new UserSpawnService();
        for (int i = 0; i < building.getNumberOfFloors(); ++i) {
            userSpawnService.startSpawnForFloor(building.getFloors().get(i));
        }

        final ElevatorService elevatorService = new ElevatorService();
        for (int i = 0; i < building.getNumberOfElevators(); ++i) {
            Elevator elevator = building.getElevators().get(i);
            elevatorService.startDeliver(elevator, building.getFloors());
        }
    }
}
