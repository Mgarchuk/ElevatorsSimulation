package modelsTest;
import configuration.BuildingConfig;
import models.Building;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BuildingTest {
    BuildingConfig buildingConfig = new BuildingConfig();
    @Test
    public void createBuildingTest() {

        assertEquals(buildingConfig.getNumberOfFloors(), 9);
        assertEquals(buildingConfig.getNumberOfElevators(), 2);
        assertEquals(buildingConfig.getElevatorsConfigs().size(), buildingConfig.getNumberOfElevators());

        Building building = new Building(buildingConfig);
        assertEquals(building.getElevators().size(), buildingConfig.getNumberOfElevators());
        assertEquals(building.getFloors().size(), buildingConfig.getNumberOfFloors());
        assertEquals(building.getNumberOfElevators(), buildingConfig.getNumberOfElevators());
        assertEquals(building.getNumberOfFloors(), buildingConfig.getNumberOfFloors());
    }

    @Test
    public void getNumberOfElevatorsTest() {
        Building building = new Building(buildingConfig);
        assertEquals(building.getNumberOfElevators(), buildingConfig.getNumberOfElevators());
    }

    @Test
    public void getNumberOfFloorsTest() {
        Building building = new Building(buildingConfig);
        assertEquals(building.getNumberOfFloors(), buildingConfig.getNumberOfFloors());
    }

    @Test
    public void getElevatorsTest() {
        Building building = new Building(buildingConfig);
        assertEquals(building.getElevators().size(), buildingConfig.getNumberOfElevators());
    }

    @Test
    public void getFloorsTest() {
        Building building = new Building(buildingConfig);
        assertEquals(building.getFloors().size(), buildingConfig.getNumberOfFloors());
    }
}
