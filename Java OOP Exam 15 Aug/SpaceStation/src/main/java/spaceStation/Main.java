package spaceStation;

import spaceStation.core.Controller;
import spaceStation.core.ControllerImpl;
import spaceStation.core.Engine;
import spaceStation.core.EngineImpl;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MissionImpl mission = new MissionImpl();
        Planet planet = new PlanetImpl("Vegeta");
        planet.getItems().add("Jelqzo");
        planet.getItems().add("Sila");
        planet.getItems().add("Dragon Balls");
        List<Astronaut> astronauts = new ArrayList<>();
        astronauts.add(new Biologist("ivan"));
                mission.explore(planet, astronauts);
        System.out.println(planet.getItems());
    }
}
