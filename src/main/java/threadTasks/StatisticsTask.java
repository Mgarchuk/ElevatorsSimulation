package threadTasks;

import lombok.extern.slf4j.Slf4j;
import models.Floor;

import java.util.List;
import java.util.TimerTask;

@Slf4j
public class StatisticsTask extends TimerTask {
    private final List<Floor> floors;

    public StatisticsTask(List<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public void run() {
        for (int i = 0; i < floors.size(); ++i) {
            log.info("Queue in floor " + (i + 1) + " to upper floor: " + floors.get(i).getUpQueueString());
            log.info("Queue in floor " + (i + 1) + " to lower floor: " + floors.get(i).getDownQueueString());
        }
    }
}
