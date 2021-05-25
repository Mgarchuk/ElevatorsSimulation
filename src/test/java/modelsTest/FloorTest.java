package modelsTest;

import models.Floor;
import models.Human;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class FloorTest {
    private final Random random = new Random();

    @Test
    public void createAndGetFloorTest() {
        for (int num = 1; num < 10; ++num) {
            Floor firstFloor = new Floor(num);
            assertEquals(firstFloor.getNumber(), 1);
        }
    }

    @Test
    public void getQueueUpTest() {
        Floor floor = new Floor(3);
        for (int i = 4; i <= 5; ++i) {
            floor.addHumanToQueue(new Human(40, i));
        }
        assertEquals(floor.getQueueUp().size(), 2);
    }

    @Test
    public void getQueueDownTest() {
        Floor floor = new Floor(5);
        for (int i = 1; i <= 4; ++i) {
            floor.addHumanToQueue(new Human(50, i));
        }
        assertEquals(floor.getQueueDown().size(), 4);
    }

    @Test
    public void setAndGetUpSignalTest() {
        Floor floor = new Floor(5);
        floor.setUpSignal(true);
        assertTrue(floor.isUpSignal());

        floor.setUpSignal(false);
        assertFalse(floor.isUpSignal());
    }

    @Test
    public void setAndGetDownSignalTest() {
        Floor floor = new Floor(5);
        floor.setDownSignal(true);
        assertTrue(floor.isDownSignal());

        floor.setDownSignal(false);
        assertFalse(floor.isDownSignal());
    }

    @Test
    public void setAndGetTotalAddedTest() {
        Floor floor = new Floor(5);
        assertEquals(floor.getTotalAdded().toString(), "0");

        for (int i = 6; i <= 9; ++i) {
            floor.addHumanToQueue(new Human(50, i));
        }
        assertEquals(floor.getQueueUp().size(), 4);

        for (Human human : floor.getQueueUp()) {
            human.drop();
        }

        floor.removeFromUpQueue();
        assertEquals(floor.getQueueUp().size(), 0);
        assertEquals(floor.getTotalAdded().toString(), "4");

        floor.addHumanToQueue(new Human(34, 2));
        assertEquals(floor.getTotalAdded().toString(), "5");
    }

    @Test
    public void setAndGetTotalDroppedTest() {
        Floor floor = new Floor(5);
        assertEquals(floor.getTotalDropped().toString(), "0");
        for (int i = 6; i <= 9; ++i) {
            floor.addHumanToQueue(new Human(50, i));
        }
        assertEquals(floor.getQueueUp().size(), 4);
        assertEquals(floor.getTotalDropped().toString(), "0");

        for (Human human : floor.getQueueUp()) {
            human.setDropped(true);
        }

        floor.removeFromUpQueue();
        assertEquals(floor.getQueueUp().size(), 0);
        assertEquals(floor.getTotalDropped().toString(), "4");

        floor.addHumanToQueue(new Human(34, 2));
        floor.addHumanToQueue(new Human(44, 1));

        assertEquals(floor.getTotalDropped().toString(), "4");

        for (Human human : floor.getQueueDown()) {
            human.setDropped(true);
        }
        floor.removeFromDownQueue();
        assertEquals(floor.getTotalDropped().toString(), "6");

    }

    @Test
    public void addHumanToQueueTest() {
        Floor floor = new Floor(5);
        for (int i = 1; i <= 10; ++i) {
            floor.addHumanToQueue(new Human(50, i));
        }

        assertEquals(floor.getQueueUp().size(), 5);
        assertEquals(floor.getQueueDown().size(), 4);
    }

    @Test
    public void removeFromUpQueueTest() {
        Floor floor = new Floor(5);
        for (int i = 6; i <= 9; ++i) {
            floor.addHumanToQueue(new Human(50, i));
        }
        assertEquals(floor.getQueueUp().size(), 4);

        for (Human human : floor.getQueueUp()) {
            human.setDropped(true);
        }

        floor.removeFromUpQueue();
        assertEquals(floor.getQueueUp().size(), 0);
    }

    @Test
    public void removeFromDownQueueTest() {
        Floor floor = new Floor(5);
        for (int i = 1; i <= 4; ++i) {
            floor.addHumanToQueue(new Human(50, i));
        }
        assertEquals(floor.getQueueDown().size(), 4);

        for (Human human : floor.getQueueDown()) {
            human.setDropped(true);
        }

        floor.removeFromDownQueue();
        assertEquals(floor.getQueueDown().size(), 0);
    }

    @Test
    public void getUpQueueStringTest() {
        Floor floor = new Floor(5);
        for (int i = 6; i <= 10; ++i) {
            floor.addHumanToQueue(new Human(50, i));
        }

        assertEquals(floor.getUpQueueString(), "size: 5 [ 50.0 50.0 50.0 50.0 50.0]");
    }

    @Test
    public void getDownQueueStringTest() {
        Floor floor = new Floor(3);
        for (int i = 1; i <= 2; ++i) {
            floor.addHumanToQueue(new Human(30, i));
        }

        assertEquals(floor.getDownQueueString(), "size: 2 [ 30.0 30.0]");
    }
}
