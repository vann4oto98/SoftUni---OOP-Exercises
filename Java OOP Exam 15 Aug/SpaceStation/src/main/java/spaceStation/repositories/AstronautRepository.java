package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AstronautRepository implements Repository<Astronaut> {

    private List<Astronaut> astronauts = new ArrayList<>();


    @Override
    public Collection<Astronaut> getModels() {
        return Collections.unmodifiableList(astronauts);
    }

    @Override
    public void add(Astronaut model) {
        this.astronauts.add(model);
    }

    @Override
    public boolean remove(Astronaut model) {
        return astronauts.remove(model);
    }

    @Override
    public Astronaut findByName(String name) {

        for (Astronaut astronaut : astronauts) {
            if (astronaut.getName().equals(name)){
                return astronaut;
            }
        }
        return null;
    }
}
