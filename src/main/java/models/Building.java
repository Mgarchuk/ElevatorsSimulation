package models;

import configuration.BuildingConfig;
import configuration.ElevatorConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
        IntStream.range(0, numberOfFloors)
                .mapToObj(i -> new Floor(i + 1))
                .forEach(floors::add);

        IntStream.range(0, numberOfElevators)
                .mapToObj(i -> config.getElevatorsConfigs().get(i))
                .map(elevatorConfig -> new Elevator(elevatorConfig, numberOfFloors))
                .forEach(elevators::add);

    }
}
