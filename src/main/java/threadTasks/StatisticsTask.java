package threadTasks;

import lombok.extern.slf4j.Slf4j;
import models.Elevator;
import models.Floor;

import java.util.List;
import java.util.TimerTask;
import java.util.stream.IntStream;

@Slf4j
public class StatisticsTask extends TimerTask {

    private final List<Floor> floors;
    private final List<Elevator> elevators;

    public StatisticsTask(List<Floor> floors, List<Elevator> elevators) {
        this.floors = floors;
        this.elevators = elevators;
    }

    @Override
    public void run() {

        long totalAdded = 0, totalDropped = 0;
        for (Floor floor : floors) {
            totalAdded += floor.getTotalAdded().get();
            totalDropped += floor.getTotalDropped().get();
        }

        log.info("-------------------------------STATISTICS-------------------------------------------------");
        log.info("Total added users: " + totalAdded);
        log.info("Total dropped users: " + totalDropped);

        IntStream.range(0, elevators.size())
                .mapToObj(i -> "Elevator number " + (i + 1) + " is on floor " + (elevators.get(i).getCurrentFloor())
                        + ". " + elevators.get(i).getPassengers().size() + " peoples in elevator")
                .forEach(log::info);

        log.info("Current queue in floors");

        IntStream.range(0, floors.size()).forEach(i -> {
            log.info("Queue in floor " + (i + 1) + " to upper floor: " + floors.get(i).getUpQueueString());
            log.info("Queue in floor " + (i + 1) + " to lower floor: " + floors.get(i).getDownQueueString());
        });

        log.info("-----------------------------------------------------------------------------------------");
    }
}
