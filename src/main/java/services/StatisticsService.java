package services;

import models.Floor;
import threadTasks.StatisticsTask;

import java.util.List;
import java.util.Timer;

public class StatisticsService {

    public void startCollectStatistics(List<Floor> floors) {
        final Timer statisticsTimer = new Timer(); //ToDo: configure
        statisticsTimer.schedule(new StatisticsTask(floors), 10000, 10000);
    }
}
