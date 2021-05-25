package configuration;

import lombok.Getter;
import models.Direction;

import java.util.Properties;


@Getter
public class ElevatorConfig {

    private final double capacity;
    private final double speed; // floors per sec
    private final int startFloor;
    private final Direction startDirection;
    private final int doorsOpeningTime;

    public ElevatorConfig(Properties properties) {

        capacity = Double.parseDouble(properties.getProperty("capacity", "1000"));
        speed = Double.parseDouble(properties.getProperty("speed", "0.5"));
        startFloor = Integer.parseInt(properties.getProperty("startFloor", "1"));
        startDirection = Direction.valueOf(properties.getProperty("startDirection", "UP"));
        doorsOpeningTime = Integer.parseInt(properties.getProperty("doorsOpeningTime", "300"));
    }

    public ElevatorConfig() {
        this(new Properties());
    }
}
