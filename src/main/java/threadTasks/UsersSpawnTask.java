package threadTasks;

import models.Floor;
import models.Human;

import java.util.Random;
import java.util.TimerTask;

public class UsersSpawnTask extends TimerTask {
    private final Floor floor;
    private final Random random;
    private final int maxFloor;
    private final int maxPersons;
    private final int maxWeight;

    public UsersSpawnTask(Floor floor, int maxFloor, int maxPersons, int maxWeight) {
        this.floor = floor;
        this.maxFloor = maxFloor;
        this.maxPersons = maxPersons;
        this.maxWeight = maxWeight;
        random = new Random();
    }

    @Override
    public void run() {
        int humansCount = random.nextInt(maxPersons + 1);
        for (int i = 0; i < humansCount; ++i) {
            Human human = new Human(random.nextInt(maxWeight), random.nextInt(maxFloor) + 1);
            floor.addHumanToQueue(human);

        }
    }
}
