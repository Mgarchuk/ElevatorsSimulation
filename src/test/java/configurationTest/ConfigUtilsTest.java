package configurationTest;

import configuration.BuildingConfig;
import configuration.ConfigUtils;
import configuration.ElevatorConfig;
import configuration.GeneratorConfig;
import lombok.SneakyThrows;
import models.Direction;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ConfigUtilsTest {

    @Test
    public void getArgumentTest() {
        assertEquals(ConfigUtils.getArgument("path=elevator1.properties", "elevator.properties"),
                "elevator1.properties");
        assertEquals(ConfigUtils.getArgument("Hi", "generator.properties"), "generator.properties");
    }

    @SneakyThrows
    @Test
    public void createBuildingConfigTest(){
        BuildingConfig buildingConfig = ConfigUtils.createBuildingConfig(null);
        assertEquals(buildingConfig.getNumberOfElevators(), 2);
        assertEquals(buildingConfig.getNumberOfFloors(), 9);
        assertEquals(buildingConfig.getElevatorsConfigs().size(), 2);

        buildingConfig = ConfigUtils.createBuildingConfig("gaga.ui");
        assertEquals(buildingConfig.getNumberOfElevators(), 2);
        assertEquals(buildingConfig.getNumberOfFloors(), 9);
        assertEquals(buildingConfig.getElevatorsConfigs().size(), 2);
    }

    @SneakyThrows
    @Test
    public void createElevatorConfigTest() {
        ElevatorConfig elevatorConfig = ConfigUtils.createElevatorConfig(null);
        assertEquals(elevatorConfig.getCapacity(), 1000);
        assertEquals(elevatorConfig.getStartFloor(), 1);
        assertEquals(elevatorConfig.getSpeed(), 0.5);
        assertEquals(elevatorConfig.getStartDirection(), Direction.UP);
        assertEquals(elevatorConfig.getDoorsOpeningTime(), 300);

        elevatorConfig = ConfigUtils.createElevatorConfig("was.por");
        assertEquals(elevatorConfig.getCapacity(), 1000);
        assertEquals(elevatorConfig.getStartFloor(), 1);
        assertEquals(elevatorConfig.getSpeed(), 0.5);
        assertEquals(elevatorConfig.getStartDirection(), Direction.UP);
        assertEquals(elevatorConfig.getDoorsOpeningTime(), 300);
    }

    @SneakyThrows
    @Test
    public void createGeneratorConfigTest() {
        GeneratorConfig generatorConfig = ConfigUtils.createGeneratorConfig(null);
        assertEquals(generatorConfig.getMaxWeight(), 150);
        assertEquals(generatorConfig.getGenerationFrequency(), 5000);
        assertEquals(generatorConfig.getStatisticsFrequency(), 10000);
        assertEquals(generatorConfig.getMaxPersonsPerTime(), 5);

        generatorConfig = ConfigUtils.createGeneratorConfig("genera.prop");
        assertEquals(generatorConfig.getMaxWeight(), 150);
        assertEquals(generatorConfig.getGenerationFrequency(), 5000);
        assertEquals(generatorConfig.getStatisticsFrequency(), 10000);
        assertEquals(generatorConfig.getMaxPersonsPerTime(), 5);
    }

    @Test
    public void getBuildingConfigTest() {
        String[] wrongArgs = {"Marina", "configName", "--conf"};
        assertNull(ConfigUtils.getBuildingConfigName(wrongArgs));

        String[] correctArgs = {"--buildingConfig=haha.prop"};
        assertEquals(ConfigUtils.getBuildingConfigName(correctArgs), "haha.prop");
    }

    @Test
    public void getGeneratorConfigTest() {
        String[] wrongArgs = {"Hoho", "Haha", "--conf"};
        assertNull(ConfigUtils.getGeneratorConfigName(wrongArgs));

        String[] correctArgs = {"--generatorConfig=mond.prp"};
        assertEquals(ConfigUtils.getGeneratorConfigName(correctArgs), "mond.prp");
    }

}
