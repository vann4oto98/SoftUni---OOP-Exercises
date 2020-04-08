package spaceStation.models.astronauts;

import spaceStation.models.bags.Bag;

public interface Astronaut {
    String getName();

    double getOxygen();

    Bag getBag();

    void breath();

    boolean canBreathe();
}
