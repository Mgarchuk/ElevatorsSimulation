package services;

import models.Floor;
import threadTasks.UsersSpawnTask;

import java.util.Timer;

public class UserSpawnService {

    public void startSpawnForFloor(Floor floor) {
        final Timer userSpawnTimer = new Timer();
        userSpawnTimer.schedule(new UsersSpawnTask(floor),
                0, 5000); // ToDo: configure
    }
}
