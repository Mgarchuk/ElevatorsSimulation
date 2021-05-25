package configurationTest;

import configuration.ElevatorConfig;
import configuration.GeneratorConfig;
import models.Direction;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratorConfigTest {

    @Test
    public void createGeneratorConfigTest() {
        Properties properties = new Properties();
        properties.setProperty("maxPersonsPerTime", "4");
        properties.setProperty("generationFrequency", "3000");
        properties.setProperty("statisticsFrequency", "15000");
        properties.setProperty("maxWeight", "170");

        GeneratorConfig generatorConfig = new GeneratorConfig(properties);

        assertEquals(generatorConfig.getMaxWeight(), 170);
        assertEquals(generatorConfig.getMaxPersonsPerTime(), 4);
        assertEquals(generatorConfig.getGenerationFrequency(), 3000);
        assertEquals(generatorConfig.getStatisticsFrequency(), 15000);

        generatorConfig = new GeneratorConfig();
        assertEquals(generatorConfig.getMaxWeight(), 150);
        assertEquals(generatorConfig.getMaxPersonsPerTime(), 5);
        assertEquals(generatorConfig.getGenerationFrequency(), 5000);
        assertEquals(generatorConfig.getStatisticsFrequency(), 10000);
    }

    @Test
    public void getMaxWeightTest() {
        Properties properties = new Properties();
        properties.setProperty("maxWeight", "170");

        GeneratorConfig generatorConfig = new GeneratorConfig(properties);
        assertEquals(generatorConfig.getMaxWeight(), 170);
    }

    @Test
    public void getMaxPersonsPerTimeTest() {
        Properties properties = new Properties();
        properties.setProperty("maxPersonsPerTime", "4");

        GeneratorConfig generatorConfig = new GeneratorConfig(properties);
        assertEquals(generatorConfig.getMaxPersonsPerTime(), 4);
    }

    @Test
    public void getGenerationFrequency() {
        Properties properties = new Properties();
        properties.setProperty("generationFrequency", "3000");

        GeneratorConfig generatorConfig = new GeneratorConfig(properties);
        assertEquals(generatorConfig.getGenerationFrequency(), 3000);

    }

    @Test
    public void getStatisticsFrequency() {
        Properties properties = new Properties();
        properties.setProperty("statisticsFrequency", "15000");

        GeneratorConfig generatorConfig = new GeneratorConfig(properties);
        assertEquals(generatorConfig.getStatisticsFrequency(), 15000);
    }
}
