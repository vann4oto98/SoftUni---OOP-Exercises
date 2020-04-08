package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class MissionImpl implements Mission {

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        List<String> items = new ArrayList<>(planet.getItems());

        astronauts.stream().filter(Astronaut::canBreathe).forEach(astronaut -> {
            while (astronaut.getOxygen()>0) {
                if (items.size()>0) {

                    astronaut.getBag()
                            .getItems()
                            .add((items.remove(0)));
                    planet.getItems().remove(planet.getItems().iterator().next());
                    astronaut.breath();

                }  else {
                    return;
                }
            }
        });

    }
}
