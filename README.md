# ElevatorsSimulation
Project for simulating many elevators work in one building using multithreading.

# Configuration
Building parameters are set in the properties file.
In this config, parameters such as the number of floors, the number of elevators and the path to the config of each elevator are set. 
If some data is missing, they are set by default.
The elevator config file contains parameters such as elevator capacity, its speed (floors per second), elevator starting floor, door opening / closing speed and starting direction.
The application also contains the generator config, which indicates the maximum number of people that can appear on one floor in one generation cycle, the generation frequency (the number of milliseconds after which the generation will take place), the frequency of displaying statistics and the maximum weight of a person.
The path to the building config is set by the command line argument --buildingConfig=path_to_config. If this parameter is absent, then the default config will be created.
The path to the generator config is set by the command line argument --generatorConfig=path_to_config. If this parameter is absent, then the default config will be created.

# Main logic
At startup, the program creates a building from a configuration file, then creates all elevators for this building from configuration files, and starts services. The service for generating people on the floors is a timer task in a separate one, which generates a random number of people on the floors once in a while, which is configurable. The upper generation limit is also configurable.

The service for starting elevators starts a timer task in a separate thread, which moves the elevator in accordance with a configurable speed. At each iteration of the task, the elevator checks if it needs to stop at the given floor. If a stop is needed, the elevator stops, spends time opening doors, releases passengers, starts passengers so as not to overflow, closes the doors. Further, if the elevator needs to go (it was called on some floor, or there are people inside), then it goes. Otherwise, it stands

Also, in a separate stream, once in some time (configurable) statistics are displayed. The number of passengers transported, passengers disembarked, the current queue in the elevators.
