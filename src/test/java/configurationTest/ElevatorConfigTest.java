package configurationTest;

import configuration.ElevatorConfig;
import models.Direction;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevatorConfigTest {

    @Test
    public void createElevatorConfigTest() {
        Properties properties = new Properties();
        properties.setProperty("capacity", "1000");
        properties.setProperty("speed", "2");
        properties.setProperty("startFloor", "1");
        properties.setProperty("startDirection", "UP");
        properties.setProperty("doorsOpeningTime", "1000");

        ElevatorConfig elevatorConfig = new ElevatorConfig(properties);

        assertEquals(elevatorConfig.getCapacity(), 1000);
        assertEquals(elevatorConfig.getSpeed(), 2);
        assertEquals(elevatorConfig.getStartDirection(), Direction.UP);
        assertEquals(elevatorConfig.getStartFloor(), 1);
        assertEquals(elevatorConfig.getDoorsOpeningTime(), 1000);

        elevatorConfig = new ElevatorConfig();
        assertEquals(elevatorConfig.getCapacity(), 1000);
        assertEquals(elevatorConfig.getSpeed(), 0.5);
        assertEquals(elevatorConfig.getStartDirection(), Direction.UP);
        assertEquals(elevatorConfig.getStartFloor(), 1);
        assertEquals(elevatorConfig.getDoorsOpeningTime(), 300);
    }

    @Test
    public void getCapacityTest() {
        Properties properties = new Properties();
        properties.setProperty("capacity", "1000");
        ElevatorConfig elevatorConfig = new ElevatorConfig(properties);
        assertEquals(elevatorConfig.getCapacity(), 1000);
    }

    @Test
    public void getSpeedTest() {
        Properties properties = new Properties();
        properties.setProperty("speed", "0.7");
        ElevatorConfig elevatorConfig = new ElevatorConfig(properties);
        assertEquals(elevatorConfig.getSpeed(), 0.7);
    }

    @Test
    public void getStartFloorTest() {
        Properties properties = new Properties();
        properties.setProperty("startFloor", "7");
        ElevatorConfig elevatorConfig = new ElevatorConfig(properties);
        assertEquals(elevatorConfig.getStartFloor(), 7);
    }

    @Test
    public void getStartDirectionTest() {
        Properties properties = new Properties();
        properties.setProperty("startDirection", "DOWN");
        ElevatorConfig elevatorConfig = new ElevatorConfig(properties);
        assertEquals(elevatorConfig.getStartDirection(), Direction.DOWN);
    }

    @Test
    public void getDoorsOpeningTime() {
        Properties properties = new Properties();
        properties.setProperty("doorsOpeningTime", "800");
        ElevatorConfig elevatorConfig = new ElevatorConfig(properties);
        assertEquals(elevatorConfig.getDoorsOpeningTime(), 800);
    }
}
