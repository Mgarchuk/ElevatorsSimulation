package configuration;

import lombok.Getter;

import java.util.Properties;


@Getter
public class GeneratorConfig {

    private final int maxPersonsPerTime;
    private final int generationFrequency; // milliseconds
    private final int maxWeight;
    private final int statisticsFrequency; // milliseconds

    public GeneratorConfig(Properties properties) {

        maxPersonsPerTime = Integer.parseInt(properties.getProperty("maxPersonsPerTime", "5"));
        generationFrequency = Integer.parseInt(properties.getProperty("generationFrequency", "5000"));
        statisticsFrequency = Integer.parseInt(properties.getProperty("statisticsFrequency", "10000"));
        maxWeight = Integer.parseInt(properties.getProperty("maxWeight", "150"));
    }

    public GeneratorConfig() {
        this(new Properties());
    }
}
