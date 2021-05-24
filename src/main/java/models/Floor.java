package models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Setter
@Getter
public class Floor {
    private final int number;
    private final ConcurrentLinkedQueue<Human> queueUp = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Human> queueDown = new ConcurrentLinkedQueue<>();
    private boolean upSignal;
    private boolean downSignal;
    private AtomicLong totalAdded = new AtomicLong(0);
    private AtomicLong totalDropped = new AtomicLong(0);

    public Floor(int number) {
        this.number = number;
    }

    public void addHumanToQueue(Human human) {
        boolean isUp = human.getRequiredFloor() > number;
        if (isUp) {
            queueUp.add(human);
            upSignal = true;
            totalAdded.incrementAndGet();
        } else if (human.getRequiredFloor() < number){
            queueDown.add(human);
            downSignal = true;
            totalAdded.incrementAndGet();
        }
    }

    public void removeFromUpQueue() {
        int wasSize = queueUp.size();
        queueUp.removeIf(Human::isDropped);
        int newSize = queueUp.size();
        totalDropped.addAndGet(wasSize - newSize);
        if (queueUp.size() == 0) {
            upSignal = false;
        }
    }

    public void removeFromDownQueue() {
        int wasSize = queueDown.size();
        queueDown.removeIf(Human::isDropped);
        int newSize = queueDown.size();
        totalDropped.addAndGet(wasSize - newSize);
        if (queueDown.size() == 0) {
            downSignal = false;
        }
    }

    public String getUpQueueString() {
        StringBuilder buffer = new StringBuilder();
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
        StringBuilder buffer = new StringBuilder();
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
