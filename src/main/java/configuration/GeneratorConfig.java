package configuration;

import java.util.Properties;

public class GeneratorConfig {
    private final int maxPersonsPerSecond;

    public GeneratorConfig(Properties properties) {
        maxPersonsPerSecond = Integer.parseInt(properties.getProperty("personsPerSecond", "5"));
    }

    public int getMaxPersonsPerSecond() {
        return maxPersonsPerSecond;
    }
}
