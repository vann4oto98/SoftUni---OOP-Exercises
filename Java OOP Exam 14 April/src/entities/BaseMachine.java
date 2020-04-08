package entities;

import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseMachine implements Machine {
    private static final String ERROR_IN_NAME_MESSAGE = "Machine name cannot be null or empty.";
    private static final String PILOT_NULL_ERROR = "Pilot cannot be null.";
    private static final String TARGET_ERROR = "Attack target cannot be null or empty string.";
    private String name;
    private Pilot pilot;
    private double attackPoints;
    private double defensePoints;
    private double healthPoints;
    private List<String> targets;


    protected BaseMachine(String name, double attackPoints, double defencePoints, double healthPoints){
        this.setName(name);
        this.attackPoints = attackPoints;
        this.defensePoints = defencePoints;
        this.setHealthPoints(healthPoints);
        this.targets = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(ERROR_IN_NAME_MESSAGE);
        }
        this.name = name;
    }

    @Override
    public Pilot getPilot() {
        return this.pilot;
    }

    @Override
    public void setPilot(Pilot pilot) {
        if (pilot == null) {
            throw new NullPointerException(PILOT_NULL_ERROR);
        }
        this.pilot = pilot;
    }

    @Override
    public double getHealthPoints() {
        return this.healthPoints;
    }

    public void setAttackPoints(double attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setDefensePoints(double defensePoints) {
        this.defensePoints = defensePoints;
    }

    @Override
    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    @Override
    public double getAttackPoints() {
        return this.attackPoints;
    }

    @Override
    public double getDefensePoints() {
        return this.defensePoints;
    }

    @Override
    public List<String> getTargets() {
        return Collections.unmodifiableList(this.targets);
    }

    @Override
    public void attack(String target) {
        if (target == null || target.trim().isEmpty()){
            throw new IllegalArgumentException(TARGET_ERROR);
        }
        this.targets.add(target);
    }

}
