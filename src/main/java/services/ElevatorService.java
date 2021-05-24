package services;

import models.Elevator;
import models.Floor;
import threadTasks.DeliverTask;

import java.util.List;
import java.util.Timer;

public class ElevatorService {

    public void startDeliver(Elevator elevator, List<Floor> floors) {
        final Timer deliverTimer = new Timer();
        int millisecondsForFloor = (int)Math.round(1000.0 / elevator.getElevatorSpeed());
        deliverTimer.schedule(new DeliverTask(elevator, floors),
                0, millisecondsForFloor);
    }

}
