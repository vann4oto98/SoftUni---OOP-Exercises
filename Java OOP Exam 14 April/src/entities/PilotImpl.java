package entities;

import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PilotImpl implements Pilot {
    private static final String MACHINE_ERROR = "Null machine cannot be added to the pilot.";
    private String name;
    private List<Machine> machines;
    private static final String PILOT_NAME_ERROR = " Pilot name cannot be null or empty string.";

    public PilotImpl(String name){
        this.setName(name);
        this.machines = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(PILOT_NAME_ERROR);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addMachine(Machine machine) {
        if (machine == null) {
            throw new IllegalArgumentException(MACHINE_ERROR);
        }
        this.machines.add(machine);
    }

    @Override
    public List<Machine> getMachines() {
        return Collections.unmodifiableList(this.machines);
    }

    @Override
    public String report() {
        //Nelson - 1 machines
        //- Boeing
        // *Type: Fighter
        // *Health: 165.00
        // *Attack: 180.00
        // *Defense: 90.00
        // *Targets: T-72
        // *Aggressive Mode(OFF)
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - %d machines", this.getName(), this.getMachines().size()));
        sb.append(System.lineSeparator());
        this.getMachines().forEach(machine -> {
            sb.append(machine.toString())
                    .append(System.lineSeparator());

        });
        return sb.toString().trim();
    }
}
