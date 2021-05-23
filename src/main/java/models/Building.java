package models;

import configuration.BuildingConfig;
import configuration.ElevatorConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Building {
    private final List<Floor> floors = new ArrayList<>();
    private final List<Elevator> elevators = new ArrayList<>();
    private final int numberOfFloors;
    private final int numberOfElevators;

    public Building(BuildingConfig config) {
        this.numberOfFloors = config.getNumberOfFloors();
        this.numberOfElevators = config.getNumberOfElevators();
        for (int i = 0; i < numberOfFloors; ++i) {
            Floor floor = new Floor(i + 1);
            floors.add(floor);
        }
        for (int i = 0; i < numberOfElevators; ++i) {
            ElevatorConfig elevatorConfig = config.getElevatorsConfigs().get(i);
            Elevator elevator = new Elevator(elevatorConfig, numberOfFloors);
            elevators.add(elevator);
        }

    }
}
