package threadTasks;

import models.Floor;
import models.Human;

import java.util.Random;
import java.util.TimerTask;

public class UsersSpawnTask extends TimerTask {
    private final Floor floor;
    private final Random random;

    public UsersSpawnTask(Floor floor) {
        this.floor = floor;
        random = new Random();
    }

    @Override
    public void run() {
        ///ToDo: configure
        int humansCount = random.nextInt(5) + 1;
        for (int i = 0; i < humansCount; ++i) {
            Human human = new Human(random.nextInt(100), random.nextInt(9) + 1);
            floor.addHumanToQueue(human);

        }
    }
}
