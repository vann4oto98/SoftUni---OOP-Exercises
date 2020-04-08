package core;

import core.interfaces.MachineFactory;
import core.interfaces.PilotFactory;
import core.interfaces.MachinesManager;

import entities.TankImpl;
import entities.interfaces.Fighter;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;
import entities.interfaces.Tank;

import java.util.Map;
import static common.OutputMessages.*;

public class MachinesManagerImpl implements MachinesManager {
    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String, Pilot> pilots;
    private Map<String, Machine> machines;

    public MachinesManagerImpl(PilotFactory pilotFactory, MachineFactory machineFactory, Map<String, Pilot> pilots, Map<String, Machine> machines) {
     //TODO: Implement me   // not sure
        this.pilotFactory = pilotFactory;
        this.machineFactory = machineFactory;
        this.pilots = pilots;
        this.machines = machines;
    }


    @Override
    public String hirePilot(String name) {
        if (pilots.containsKey(name)){
            return String.format(pilotExists, name);
        }
        pilots.put(name, pilotFactory.createPilot(name));
        return String.format(pilotHired, name);
    }

    @Override
    public String manufactureTank(String name, double attackPoints, double defensePoints) {
        if (!machines.containsKey(name)){
            machines.put(name, machineFactory.createTank(name, attackPoints, defensePoints));
            return String.format(tankManufactured, name, attackPoints, defensePoints);
        } else {
           return String.format(machineExists, name);
        }
    }

    @Override
    public String manufactureFighter(String name, double attackPoints, double defensePoints) {
        if (!machines.containsKey(name)){
            machines.put(name, machineFactory.createFighter(name, attackPoints, defensePoints));
            return String.format(fighterManufactured, name, attackPoints, defensePoints);
        } else {
            return String.format(machineExists, name);
        }
    }

    @Override
    public String engageMachine(String selectedPilotName, String selectedMachineName) {
        boolean pilotFound = this.pilots.containsKey(selectedPilotName);
        boolean machineFound = this.machines.containsKey(selectedMachineName);
        if (pilotFound && machineFound){

            if (this.machines.get(selectedMachineName).getPilot() != null){
                return String.format(machineHasPilotAlready, selectedMachineName);
            }

            this.pilots.get(selectedPilotName)
                    .addMachine(this.machines.get(selectedMachineName));

            this.machines.get(selectedMachineName)
                    .setPilot(this.pilots.get(selectedPilotName));
            return String.format(machineEngaged, selectedPilotName, selectedMachineName);
        } else {
            if (!pilotFound){
                return String.format(pilotNotFound, selectedPilotName);
            }

            return String.format(machineNotFound, selectedMachineName);
        }
    }

    @Override
    public String attackMachines(String attackingMachineName, String defendingMachineName) {
       boolean attackingMachineExist = this.machines.containsKey(attackingMachineName);
       boolean defendingMachineExist = this.machines.containsKey(defendingMachineName);
       if (!attackingMachineExist){
           return String.format(machineNotFound, attackingMachineName);
       }
       if (!defendingMachineExist){
           return String.format(machineNotFound, defendingMachineName);
       }
       this.machines.get(attackingMachineName)
               .attack(defendingMachineName);

       double attackMachineAttackPoints = this.machines.get(attackingMachineName).getAttackPoints();
       double defendingMachineDefencePoints = this.machines.get(defendingMachineName).getDefensePoints();

       double currentHP = this.machines.get(defendingMachineName).getHealthPoints();
       if (attackMachineAttackPoints > defendingMachineDefencePoints){

           this.machines.get(defendingMachineName)
                   .setHealthPoints(currentHP - (attackMachineAttackPoints - defendingMachineDefencePoints));

           currentHP = this.machines.get(defendingMachineName).getHealthPoints();
           if (currentHP<0){
               this.machines.get(defendingMachineName).setHealthPoints(0);
               currentHP = 0;
           }
       }
       return String.format(attackSuccessful, defendingMachineName, attackingMachineName, currentHP);
    }

    @Override
    public String pilotReport(String pilotName) {
        if (this.pilots.containsKey(pilotName)){
            return this.pilots.get(pilotName).report();
        }
        return String.format(pilotNotFound, pilotName);
    }

    @Override
    public String toggleFighterAggressiveMode(String fighterName) {
        if (this.machines.get(fighterName) instanceof Fighter){
         ((Fighter) this.machines.get(fighterName)).toggleAggressiveMode();
         return String.format(fighterOperationSuccessful, fighterName);
        }
       return String.format(notSupportedOperation, fighterName);
    }

    @Override
    public String toggleTankDefenseMode(String tankName) {
        if (this.machines.get(tankName) instanceof Tank){
            ((Tank) this.machines.get(tankName)).toggleDefenseMode();
            return String.format(tankOperationSuccessful, tankName);
        }
        return String.format(notSupportedOperation, tankName);
    }
}
