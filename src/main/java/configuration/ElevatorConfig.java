package configuration;

import models.Direction;

import java.util.Properties;

public class ElevatorConfig {
    private final double capacity;
    private final double speed; /// floors per sec
    private final int startFloor;
    private final Direction startDirection;

    public ElevatorConfig(Properties properties) {
        capacity = Double.parseDouble(properties.getProperty("capacity", "1000"));
        speed = Double.parseDouble(properties.getProperty("speed", "0.5"));
        startFloor = Integer.parseInt(properties.getProperty("startFloor", "1"));
        startDirection = Direction.valueOf(properties.getProperty("startDirection", "UP"));
    }

    public double getCapacity() {
        return capacity;
    }

    public double getSpeed() {
        return speed;
    }

    public Direction getStartDirection() {
        return startDirection;
    }

    public int getStartFloor() {
        return startFloor;
    }
}
