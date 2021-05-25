package servicesTest;

import configuration.ElevatorConfig;
import models.Elevator;
import models.Floor;
import org.junit.jupiter.api.Test;
import services.ElevatorService;
import services.UserSpawnService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ElevatorServiceTest {
    private final ElevatorConfig config = new ElevatorConfig();
    final ElevatorService elevatorService = new ElevatorService();
    final UserSpawnService spawnService = new UserSpawnService();

    public List<Floor> createFloors(int n) {
        List<Floor> floors = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            floors.add(new Floor(i));
        }
        return floors;
    }

    @Test
    public void elevatorDontMoveIfFloorsEmptyTest() throws InterruptedException {
        List<Floor> floors = createFloors(10);
        Elevator elevator = new Elevator(config, 10);
        int millisecondsForFloor = (int)Math.round(1000.0 / elevator.getElevatorSpeed());

        elevatorService.startDeliver(elevator, floors);
        assertEquals(config.getStartFloor(), elevator.getCurrentFloor());
        Thread.sleep(millisecondsForFloor + 100);
        assertEquals(config.getStartFloor(), elevator.getCurrentFloor());
    }

    @Test
    public void elevatorCollectHumansTest() throws InterruptedException {
        List<Floor> floors = createFloors(10);

        spawnService.startSpawnForFloor(floors.get(config.getStartFloor() - 1), 10, 400, 5, 50);
        Thread.sleep(400);
        int size = floors.get(config.getStartFloor() - 1).getQueueDown().size() + floors.get(config.getStartFloor() - 1).getQueueUp().size();
        assertTrue(size >= 0 && size <= 10);

        Elevator elevator = new Elevator(config, 10);
        elevatorService.startDeliver(elevator, floors);
        Thread.sleep(1000);
        size = floors.get(config.getStartFloor() - 1).getQueueDown().size() + floors.get(config.getStartFloor() - 1).getQueueUp().size();
        assertTrue(elevator.getPassengers().size() > 0 || size == 0);
    }
}
