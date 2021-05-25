package modelsTest;

import models.Human;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HumanTest {

    @Test
    public void createHumanTest() {
        Human human = new Human(123, 22);
        assertEquals(human.getRequiredFloor(), 22);
        assertEquals(human.getWeight(), 123);
        assertFalse(human.isDropped());

        assertThrows(IllegalArgumentException.class, () -> new Human(-123, 22));
        assertThrows(IllegalArgumentException.class, () -> human.setRequiredFloor(-23));
        assertThrows(IllegalArgumentException.class, () -> human.setRequiredFloor(0));
    }

    @Test
    public void getWeightTest() {
        Human human = new Human(13, 22);
        assertEquals(human.getWeight(), 13);
    }

    @Test
    public void setAndGetRequiredFloorTest() {
        Human human = new Human(13, 22);
        human.setRequiredFloor(12);

        assertEquals(human.getRequiredFloor(), 12);
    }

    @Test
    public void setAndIsDroppedTest() {
        Human human = new Human(13, 22);
        assertFalse(human.isDropped());
        human.setDropped(true);
        assertTrue(human.isDropped());
    }

    @Test
    public void dropTest() {
        Human human = new Human(13, 22);
        assertFalse(human.isDropped());
        human.drop();
        assertTrue(human.isDropped());
    }

    @Test
    public void toStringTest() {
        Human human = new Human(25, 2);
        assertEquals(human.toString(), "25.0");
    }

}
