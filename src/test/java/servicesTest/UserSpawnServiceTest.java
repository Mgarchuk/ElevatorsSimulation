package servicesTest;
import models.Floor;
import models.Human;
import org.junit.jupiter.api.Test;
import services.UserSpawnService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSpawnServiceTest {

    final UserSpawnService service = new UserSpawnService();

    @Test
    public void maxUsersBorderTest() throws InterruptedException {
        int maxPersons = 5, maxWeight = 100;
        Floor floor = new Floor(5);
        service.startSpawnForFloor(floor, 10, 100, maxPersons, maxWeight);
        for (int i = 0; i < 5; ++i) {
            assertTrue(floor.getQueueDown().size() + floor.getQueueUp().size() <= maxPersons * (i + 1));
            Thread.sleep(100);
        }
    }

    @Test
    public void correctHumansTest() throws InterruptedException {
        int maxPersons = 5, maxWeight = 100;
        Floor floor = new Floor(5);
        service.startSpawnForFloor(floor, 10, 100, maxPersons, maxWeight);
        for (int i = 0; i < 5; ++i) {
            Collection<Human> humans = floor.getQueueUp();
            for (Human human : humans) {
                assertTrue(human.getRequiredFloor() >= 1 && human.getRequiredFloor() <= 10);
                assertTrue(human.getWeight() >= 0 && human.getRequiredFloor() <= maxWeight);
            }
            Thread.sleep(100);
        }
    }

    @Test
    public void oneFloorGenerationTest() throws InterruptedException {
        int maxPersons = 5, maxWeight = 100;
        Floor floor = new Floor(5);
        Floor floor2 = new Floor(6);
        service.startSpawnForFloor(floor, 10, 100, maxPersons, maxWeight);
        for (int i = 0; i < 5; ++i) {
            assertEquals(floor2.getQueueDown().size() + floor2.getQueueUp().size(), 0);
        }
    }
}
