package configurationTest;

import configuration.BuildingConfig;
import lombok.extern.slf4j.Slf4j;
import models.Direction;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class BuildingConfigTest {

    @Test
    public void createBuildingConfigTest() {
        Properties firstProperties = new Properties();
        firstProperties.setProperty("numberOfFloors", "20");
        firstProperties.setProperty("numberOfElevators", "5");
        BuildingConfig firstConfig = new BuildingConfig(firstProperties);
        assertEquals(firstConfig.getNumberOfFloors(), 20);
        assertEquals(firstConfig.getNumberOfElevators(), 5);
        assertEquals(firstConfig.getElevatorsConfigs().size(), firstConfig.getNumberOfElevators());


        BuildingConfig secondConfig = new BuildingConfig();
        assertEquals(secondConfig.getNumberOfFloors(), 9);
        assertEquals(secondConfig.getNumberOfElevators(), 2);
        assertEquals(secondConfig.getElevatorsConfigs().size(), secondConfig.getNumberOfElevators());

    }

    @Test
    public void getNumberOfFloorsTest() {
        Properties properties = new Properties();
        properties.setProperty("numberOfFloors", "20");
        BuildingConfig config = new BuildingConfig(properties);

        assertEquals(config.getNumberOfFloors(), 20);
    }

    @Test
    public void getNumberOfElevatorsTest() {
        Properties properties = new Properties();
        properties.setProperty("numberOfElevators", "4");
        BuildingConfig config = new BuildingConfig(properties);

        assertEquals(config.getNumberOfElevators(), 4);
    }

    @Test
    public void getElevatorsConfigsTest() {
        BuildingConfig config = new BuildingConfig();

        assertEquals(config.getElevatorsConfigs().size(), config.getNumberOfElevators());
        assertEquals(config.getElevatorsConfigs().get(0).getCapacity(), 1000);
        assertEquals(config.getElevatorsConfigs().get(0).getSpeed(), 0.5);
        assertEquals(config.getElevatorsConfigs().get(0).getStartFloor(), 1);
        assertEquals(config.getElevatorsConfigs().get(0).getStartDirection(), Direction.UP);
        assertEquals(config.getElevatorsConfigs().get(0).getDoorsOpeningTime(), 300);
    }


}
