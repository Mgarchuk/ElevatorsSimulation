package models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Setter
@Getter
public class Floor {
    private final int number;
    private final ConcurrentLinkedQueue<Human> queueUp = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Human> queueDown = new ConcurrentLinkedQueue<>();

    public Floor(int number) {
        this.number = number;
    }

    public void addHumanToQueue(Human human) {
        boolean isUp = human.getRequiredFloor() > number;
        //log.info("New human is spawned in floor number: " + number + ". Destination floor: " + human.getRequiredFloor());
        if (isUp) {
            queueUp.add(human);
        } else {
            queueDown.add(human);
        }
    }

    public String getUpQueueString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("size: ");
        buffer.append(queueUp.size());
        buffer.append(" [");
        for (Human human : queueUp) {
            buffer.append(" ");
            buffer.append(human.toString());
        }
        buffer.append("]");
        return buffer.toString();
    }

    public String getDownQueueString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("size: ");
        buffer.append(queueDown.size());
        buffer.append(" [");
        for (Human human : queueDown) {
            buffer.append(" ");
            buffer.append(human.toString());
        }
        buffer.append("]");
        return buffer.toString();
    }
}
