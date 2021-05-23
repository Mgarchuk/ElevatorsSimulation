package threadTasks;

import lombok.extern.slf4j.Slf4j;
import models.Direction;
import models.Elevator;
import models.Floor;
import models.Human;

import java.util.List;
import java.util.TimerTask;

@Slf4j
public class DeliverTask extends TimerTask {
    private final Elevator elevator;
    private final List<Floor> floors;

    public DeliverTask(Elevator elevator, List<Floor> floors) {
        this.elevator = elevator;
        this.floors = floors;
    }

    @Override
    public void run() {
        elevator.dropPassengers();

        Floor currentFloor = floors.get(elevator.getCurrentFloor() - 1);
        int cnt = 0;
        if (elevator.getDirection() == Direction.UP) {
            for (Human newPassenger : currentFloor.getQueueUp()) {
                boolean isAdded = elevator.addPassenger(newPassenger);
                if (isAdded) {
                    cnt++;
                }
            }
        } else {
            for (Human newPassenger : currentFloor.getQueueDown()) {
                boolean isAdded = elevator.addPassenger(newPassenger);
                if (isAdded) {
                    cnt++;
                }
            }
        }
        log.info(cnt + " passengers added in floor " + elevator.getCurrentFloor());

        elevator.moveNext();
    }
}
