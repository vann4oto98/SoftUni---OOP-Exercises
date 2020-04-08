package viceCity.repositories;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GunRepository implements Repository<Gun> {
    private final List<Gun> guns;

    public GunRepository(){
        this.guns = new LinkedList<>();
    }
    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableList(this.guns);
    }

    @Override
    public void add(Gun model) {
        if (!this.guns.contains(model)){
            this.guns.add(model);
        }
    }

    @Override
    public boolean remove(Gun model) {
        return this.guns.remove(model);
    }

    @Override
    public Gun find(String name) {
        return this.guns.stream().filter(g -> g.getName().equals(name)).findFirst().orElse(null);
    }
}
