package modelsTest;

import models.Floor;
import models.Human;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloorTest {
    private final Random random = new Random();

    @Test
    public void createFloorTest() {
        for(int num = 1; num < 10; ++num) {
            Floor firstFloor = new Floor(num);
            assertEquals(firstFloor.getNumber(), 1);
        }
    }

    @Test
    public void addHumanToQueue() {
        /*
        Human human = new Human(random.nextInt(100), random.nextInt(9) + 1);
        Floor firstFloor = new Floor(1);
        assertEquals(firstFloor.getNumber(), 1);
        firstFloor.addHumanToQueue(human);
        assertEquals(firstFloor.getQueueUp().size(), 1);
         */
    }
}
