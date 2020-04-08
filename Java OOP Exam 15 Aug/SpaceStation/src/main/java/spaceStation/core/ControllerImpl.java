package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private List<Astronaut> astronauts;
    private List<Planet> planets;
    private Mission mission;
    private int exploredPlanetsCount = 0;

    public ControllerImpl(){
        this.astronauts = new ArrayList<>();
        this.planets = new ArrayList<>();
        this.mission = new MissionImpl();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        return createAstronaut(type, astronautName);
    }


    @Override
    public String addPlanet(String planetName, String... items) {
        return createPlanet(planetName, items);
    }


    @Override
    public String retireAstronaut(String astronautName) {
        return removeAstronaut(astronautName);
    }



    @Override
    public String explorePlanet(String planetName) {
        Planet planet = getPlanet(planetName);
        List<Astronaut> missionReadyAstronauts = this.astronauts.stream()
                                                                .filter(astronaut -> astronaut.getOxygen() > 60)
                                                                .collect(Collectors.toList());

        if (missionReadyAstronauts.isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }


        this.mission.explore(planet, missionReadyAstronauts);
        this.exploredPlanetsCount++;

        long deadAstronautsCount = missionReadyAstronauts.stream().filter(astronaut -> astronaut.getOxygen() == 0).count();

        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, deadAstronautsCount);
    }


    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.REPORT_PLANET_EXPLORED, this.exploredPlanetsCount));
        sb.append(System.lineSeparator());
        sb.append(ConstantMessages.REPORT_ASTRONAUT_INFO);
        for (Astronaut astronaut : this.astronauts) {
            sb.append(System.lineSeparator());
            sb.append(astronaut);
        }
        return sb.toString();
    }




    private String createAstronaut(String type, String astronautName) {
        switch (type){
            case "Biologist":
                this.astronauts.add(new Biologist(astronautName));
                break;
            case "Meteorologist":
                this.astronauts.add(new Meteorologist(astronautName));
                break;
            case "Geodesist":
                this.astronauts.add(new Geodesist(astronautName));
                break;
                default:
                    throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }
        return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
    }



    private String createPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        planet.getItems().addAll(Arrays.asList(items));
        this.planets.add(planet);
        return String.format(ConstantMessages.PLANET_ADDED, planetName);

    }

    private String removeAstronaut(String astronautName) {
        for (Astronaut astronaut : this.astronauts) {
            if (astronaut.getName().equals(astronautName)){
                this.astronauts.remove(astronaut);
                return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
            }
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
    }


    private Planet getPlanet(String planetName) {
        for (Planet planet : this.planets) {
            if (planet.getName().equals(planetName)){
                return planet;
            }
        }
        return null;
    }
}
