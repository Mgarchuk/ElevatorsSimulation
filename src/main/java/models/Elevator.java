package models;

import configuration.ElevatorConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
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

    public Elevator(ElevatorConfig config, int lastFloor) {
        liftingCapacity = config.getCapacity();
        elevatorSpeed = config.getSpeed();
        currentFloor = Math.max(1, Math.min(config.getStartFloor(), lastFloor));
        direction = config.getStartDirection();
        this.lastFloor = lastFloor;
    }

    public boolean addPassenger(Human human) {
        if (currentWeight + human.getWeight() <= liftingCapacity) {
            passengers.add(human);
            return true;
        }
        return false;
    }

    public Direction getDirection() {
        return direction;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public double getElevatorSpeed() {
        return elevatorSpeed;
    }

    public double getLiftingCapacity() {
        return liftingCapacity;
    }

    public int getCurrentFloor() {
        return currentFloor;
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
}
