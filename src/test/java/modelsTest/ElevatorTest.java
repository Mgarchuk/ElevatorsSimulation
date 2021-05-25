package modelsTest;

import configuration.BuildingConfig;
import configuration.ElevatorConfig;
import models.Direction;
import models.Elevator;
import models.Floor;
import models.Human;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class ElevatorTest {
    BuildingConfig buildingConfig = new BuildingConfig();

    @Test
    public void createElevatorTest() {
        ElevatorConfig elevatorConfig = buildingConfig.getElevatorsConfigs().get(0);
        Elevator elevator = new Elevator(elevatorConfig, 10);
        assertEquals(elevator.getLiftingCapacity(), 1000);
        assertEquals(elevator.getElevatorSpeed(), 0.5);
        assertEquals(elevator.getCurrentFloor(), 1);
        assertEquals(elevator.getDirection(), Direction.UP);
        assertEquals(elevator.getDoorsOpeningTime(), 300);

        Properties properties = new Properties();
        properties.setProperty("capacity", "1300");
        properties.setProperty("speed", "1");
        properties.setProperty("startFloor", "5");
        properties.setProperty("startDirection", "DOWN");

        elevatorConfig = new ElevatorConfig(properties);
        elevator = new Elevator(elevatorConfig, 10);
        assertEquals(elevator.getLiftingCapacity(), 1300);
        assertEquals(elevator.getElevatorSpeed(), 1);
        assertEquals(elevator.getCurrentFloor(), 5);
        assertEquals(elevator.getDirection(), Direction.DOWN);
        assertEquals(elevator.getDoorsOpeningTime(), 300);
    }

    @Test
    public void getLiftingCapacityTest() {
        Properties properties = new Properties();
        properties.setProperty("capacity", "800");
        ElevatorConfig elevatorConfig = new ElevatorConfig(properties);
        Elevator elevator = new Elevator(elevatorConfig, 10);
        assertEquals(elevator.getLiftingCapacity(), 800);

        elevatorConfig = buildingConfig.getElevatorsConfigs().get(0);
        elevator = new Elevator(elevatorConfig, 10);
        assertEquals(elevator.getLiftingCapacity(), 1000);
    }

    @Test
    public void setAndGetCurrentWeightTest() {
        ElevatorConfig elevatorConfig = buildingConfig.getElevatorsConfigs().get(0);
        Elevator elevator = new Elevator(elevatorConfig, 10);
        elevator.setCurrentWeight(10);
        assertEquals(elevator.getCurrentWeight(), 10);
        elevator.setCurrentWeight(elevator.getCurrentWeight() + 37);
        assertEquals(elevator.getCurrentWeight(), 47);
    }

    @Test
    public void getElevatorSpeedTest() {
        ElevatorConfig elevatorConfig = buildingConfig.getElevatorsConfigs().get(0);
        Elevator elevator = new Elevator(elevatorConfig, 10);
        assertEquals(elevator.getElevatorSpeed(), 0.5);
    }

    @Test
    public void addAndGetPassengersTest() {
        ElevatorConfig elevatorConfig = buildingConfig.getElevatorsConfigs().get(0);
        Elevator elevator = new Elevator(elevatorConfig, 10);
        elevator.addPassenger(new Human(100.3, 3));
        assertEquals(elevator.getPassengers().size(), 1);
        assertEquals(elevator.getPassengers().get(0).getWeight(), 100.3);
        assertEquals(elevator.getPassengers().get(0).getRequiredFloor(), 3);

        assertThrows(IllegalArgumentException.class, () -> elevator.addPassenger(new Human(-1, 22)));
    }

    @Test
    public void setAndGetCurrentFloorTest() {
        ElevatorConfig elevatorConfig = buildingConfig.getElevatorsConfigs().get(0);
        Elevator elevator = new Elevator(elevatorConfig, 10);
        elevator.setCurrentFloor(7);
        assertEquals(elevator.getCurrentFloor(), 7);
        assertThrows(IllegalArgumentException.class, () -> elevator.setCurrentFloor(33));
        assertEquals(elevator.getCurrentFloor(), 7);

    }

    @Test
    public void setAndGetDirectionTest() {
        Properties properties = new Properties();
        properties.setProperty("startDirection", "DOWN");

        ElevatorConfig elevatorConfig = new ElevatorConfig(properties);
        Elevator elevator = new Elevator(elevatorConfig, 10);
        assertEquals(elevator.getDirection(), Direction.DOWN);
    }

    @Test
    public void getLastFloorTest() {
        ElevatorConfig elevatorConfig = buildingConfig.getElevatorsConfigs().get(0);
        Elevator elevator = new Elevator(elevatorConfig, 10);
        assertEquals(elevator.getLastFloor(), 10);
    }

    @Test
    public void getDoorOpeningTimeTest() {
        ElevatorConfig elevatorConfig = buildingConfig.getElevatorsConfigs().get(0);
        Elevator elevator = new Elevator(elevatorConfig, 10);
        assertEquals(elevator.getDoorsOpeningTime(), 300);
    }

    @Test
    public void hasPassengerForExitOnFloorTest() {
        ElevatorConfig elevatorConfig = buildingConfig.getElevatorsConfigs().get(0);
        Elevator elevator = new Elevator(elevatorConfig, 10);
        elevator.addPassenger(new Human(100.3, 3));
        elevator.addPassenger(new Human(23.2, 2));

        assertTrue(elevator.hasPassengerForExitOnFloor(new Floor(2)));
        assertTrue(elevator.hasPassengerForExitOnFloor(new Floor(3)));
        assertFalse(elevator.hasPassengerForExitOnFloor(new Floor(4)));
    }

    @Test
    public void dropPassengersTest() {
        ElevatorConfig elevatorConfig = buildingConfig.getElevatorsConfigs().get(0);
        Elevator elevator = new Elevator(elevatorConfig, 10);

        elevator.addPassenger(new Human(22.5, 5));
        elevator.addPassenger(new Human(100.3, 3));
        elevator.addPassenger(new Human(23.2, 2));
        elevator.addPassenger(new Human(43.5, 5));

        assertEquals(elevator.getPassengers().size(), 4);

        elevator.setCurrentFloor(5);
        elevator.dropPassengers();

        assertEquals(elevator.getPassengers().size(), 2);
        assertEquals(elevator.getPassengers().get(0).getWeight(), 100.3);
        assertEquals(elevator.getPassengers().get(0).getRequiredFloor(), 3);
        assertEquals(elevator.getPassengers().get(1).getWeight(), 23.2);
        assertEquals(elevator.getPassengers().get(1).getRequiredFloor(), 2);

    }

    @Test
    public void moveNextTest() {
        ElevatorConfig elevatorConfig = buildingConfig.getElevatorsConfigs().get(0);
        Elevator elevator = new Elevator(elevatorConfig, 10);

        assertEquals(elevator.getCurrentFloor(), 1);
        elevator.moveNext();
        assertEquals(elevator.getCurrentFloor(), 2);

        elevator.setCurrentFloor(10);
        elevator.moveNext();
        assertEquals(elevator.getCurrentFloor(), 9);

    }

    @Test
    public void changeDirectionIfNeed() {
        ElevatorConfig elevatorConfig = buildingConfig.getElevatorsConfigs().get(0);
        Elevator elevator = new Elevator(elevatorConfig, 10);

        assertEquals(elevator.getDirection(), Direction.UP);
        elevator.setCurrentFloor(10);
        elevator.moveNext();
        assertEquals(elevator.getDirection(), Direction.DOWN);
    }
}
