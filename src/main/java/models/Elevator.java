package models;

import configuration.ElevatorConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Slf4j
public class Elevator {
    private final double liftingCapacity;
    private double currentWeight = 0;
    private final double elevatorSpeed;
    private final List<Human> passengers = new ArrayList<>();
    private int currentFloor;
    private Direction direction;
    private final int lastFloor;
    private final int doorsOpeningTime;

    public Elevator(ElevatorConfig config, int lastFloor) {
        if (config.getSpeed() < 0 || lastFloor < 1 || config.getCapacity() < 0 || config.getStartFloor() < 1 ||
                config.getStartFloor() > lastFloor || config.getDoorsOpeningTime() < 0) {
            throw new IllegalArgumentException("Wrong config");
        }
        liftingCapacity = config.getCapacity();
        elevatorSpeed = config.getSpeed();
        currentFloor = Math.max(1, Math.min(config.getStartFloor(), lastFloor));
        direction = config.getStartDirection();
        doorsOpeningTime = config.getDoorsOpeningTime();
        this.lastFloor = lastFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        if (currentFloor > lastFloor || currentFloor < 1) throw new IllegalArgumentException("Wrong floor number");
        this.currentFloor = currentFloor;
    }

    public boolean hasPassengerForExitOnFloor(Floor floor) {
        for (Human passenger : passengers) {
            if (passenger.getRequiredFloor() == floor.getNumber()) {
                return true;
            }
        }
        return false;
    }

    public boolean addPassenger(Human human) {
        if (human == null) {
            return false;
        }
        if (currentWeight + human.getWeight() <= liftingCapacity) {
            passengers.add(human);
            return true;
        }
        return false;
    }

    public void dropPassengers() {
        int cnt = 0;
        for (Human passenger : passengers) {
            if (passenger.getRequiredFloor() == currentFloor) {
                cnt++;
            }
        }
        log.info(cnt + " passengers drops from floor number " + currentFloor);
        passengers.removeIf(p -> (p.getRequiredFloor() == currentFloor));
    }

    public void moveNext() {
        if (direction == Direction.UP) {
            if (currentFloor < lastFloor) {
                currentFloor++;
            } else {
                currentFloor = lastFloor - 1;
                direction = Direction.DOWN;
            }
        } else {
            if (currentFloor > 1) {
                currentFloor--;
            } else {
                currentFloor = 2;
                direction = Direction.UP;
            }
        }
    }

    public void changeDirectionIfNeed() {
        if (currentFloor == lastFloor) {
            if (direction == Direction.UP) {
                direction = Direction.DOWN;
            }
        } else if (currentFloor == 1) {
            if (direction == Direction.DOWN) {
                direction = Direction.UP;
            }
        }
    }
}
