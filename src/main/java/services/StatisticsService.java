package services;

import models.Elevator;
import models.Floor;
import threadTasks.StatisticsTask;

import java.util.List;
import java.util.Timer;

public class StatisticsService {

    public void startCollectStatistics(List<Floor> floors, List<Elevator> elevators, int statisticsFrequency) {

        final Timer statisticsTimer = new Timer();
        statisticsTimer.schedule(new StatisticsTask(floors, elevators), statisticsFrequency, statisticsFrequency);
    }
}
