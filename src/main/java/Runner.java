import configuration.BuildingConfig;
import configuration.ConfigUtils;
import configuration.GeneratorConfig;
import lombok.extern.slf4j.Slf4j;
import models.Building;
import models.Elevator;
import services.ElevatorService;
import services.StatisticsService;
import services.UserSpawnService;

@Slf4j
public class Runner {

    public static void main(String[] args) {

        BuildingConfig config = null;
        GeneratorConfig generatorConfig = null;

        try {
            config = ConfigUtils.createBuildingConfig(ConfigUtils.getBuildingConfigName(args));
            generatorConfig = ConfigUtils.createGeneratorConfig(ConfigUtils.getGeneratorConfigName(args));
        } catch (Exception ex) {
            log.error("Config error");
            ex.printStackTrace();
        }

        if (config == null || generatorConfig == null) {
            log.error("Can't find configs");
            return;
        }

        final Building building = new Building(config);
        final UserSpawnService userSpawnService = new UserSpawnService();

        for (int i = 0; i < building.getNumberOfFloors(); ++i) {
            userSpawnService.startSpawnForFloor(building.getFloors().get(i), building.getNumberOfFloors(),
                    generatorConfig.getGenerationFrequency(), generatorConfig.getMaxPersonsPerTime(),
                    generatorConfig.getMaxWeight());
        }

        final ElevatorService elevatorService = new ElevatorService();

        for (int i = 0; i < building.getNumberOfElevators(); ++i) {
            Elevator elevator = building.getElevators().get(i);
            elevatorService.startDeliver(elevator, building.getFloors());
        }

        final StatisticsService statisticsService = new StatisticsService();
        statisticsService.startCollectStatistics(building.getFloors(), building.getElevators(),
                generatorConfig.getStatisticsFrequency());
    }
}
