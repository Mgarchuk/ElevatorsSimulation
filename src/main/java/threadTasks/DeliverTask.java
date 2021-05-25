package threadTasks;

import lombok.SneakyThrows;
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

    @SneakyThrows
    @Override
    public void run() {

        elevator.changeDirectionIfNeed();
        Floor currentFloor = floors.get(elevator.getCurrentFloor() - 1);

        boolean isNeedToStop = false;
        if (currentFloor.isUpSignal() && elevator.getDirection() == Direction.UP) {
            isNeedToStop = true;
        }
        if (currentFloor.isDownSignal() && elevator.getDirection() == Direction.DOWN) {
            isNeedToStop = true;
        }
        if (elevator.hasPassengerForExitOnFloor(currentFloor)) {
            isNeedToStop = true;
        }

        if (isNeedToStop) {
            Thread.sleep(elevator.getDoorsOpeningTime()); /// doors opening
            elevator.dropPassengers();

            int count = 0;
            if (elevator.getDirection() == Direction.UP || currentFloor.getNumber() == 1) {
                for (Human newPassenger : currentFloor.getQueueUp()) {
                    boolean isAdded = elevator.addPassenger(newPassenger);
                    if (isAdded) {
                        newPassenger.drop();
                        count++;
                    }
                }
                currentFloor.removeFromUpQueue();
            } else if (elevator.getDirection() == Direction.DOWN) {
                for (Human newPassenger : currentFloor.getQueueDown()) {
                    boolean isAdded = elevator.addPassenger(newPassenger);
                    if (isAdded) {
                        newPassenger.drop();
                        count++;
                    }
                    currentFloor.removeFromDownQueue();
                }
            }
            log.info(count + " passengers added in floor " + elevator.getCurrentFloor());

            Thread.sleep(elevator.getDoorsOpeningTime()); /// doors closed
        }

        if (elevator.getPassengers().size() > 0) {
            elevator.moveNext();
        } else {
            boolean hasDownSignal = false, hasUpSignal = false;
            for (int i = 0; i < floors.size(); ++i) {
                if (floors.get(i).getNumber() < currentFloor.getNumber()) {
                    if (floors.get(i).isUpSignal() || floors.get(i).isDownSignal()) {
                        hasDownSignal = true;
                    }
                } else if (floors.get(i).getNumber() > currentFloor.getNumber()) {
                    if (floors.get(i).isUpSignal() || floors.get(i).isDownSignal()) {
                        hasUpSignal = true;
                    }
                }
            }

            if (elevator.getDirection() == Direction.UP) {
                if (hasUpSignal) {
                    elevator.moveNext();
                } else if (hasDownSignal) {
                    elevator.setDirection(Direction.DOWN);
                    elevator.moveNext();
                }
            } else {
                if (hasDownSignal) {
                    elevator.moveNext();
                } else if (hasUpSignal) {
                    elevator.setDirection(Direction.UP);
                    elevator.moveNext();
                }
            }
        }

    }
}
