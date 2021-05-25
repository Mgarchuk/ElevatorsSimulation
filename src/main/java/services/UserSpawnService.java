package services;

import models.Floor;
import threadTasks.UsersSpawnTask;

import java.util.Timer;

public class UserSpawnService {

    public void startSpawnForFloor(Floor floor, int maxFloor, int spawnFrequency, int maxPersons, int maxWeight) {

        final Timer userSpawnTimer = new Timer();
        userSpawnTimer.schedule(new UsersSpawnTask(floor, maxFloor, maxPersons, maxWeight),
                0, spawnFrequency);
    }
}
