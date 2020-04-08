import core.MachineFactoryImpl;
import core.MachinesManagerImpl;

import core.PilotFactoryImpl;
import core.interfaces.MachineFactory;
import core.interfaces.PilotFactory;
import core.interfaces.MachinesManager;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PilotFactory pilotFactory = new PilotFactoryImpl(); //TODO change null with your implementation
        MachineFactory machineFactory = new MachineFactoryImpl(); //TODO change null with your implementation
        Map<String, Pilot> pilots = new LinkedHashMap<>();
        Map<String, Machine> machines = new LinkedHashMap<>();

        MachinesManager machinesManager = new MachinesManagerImpl(pilotFactory, machineFactory, pilots, machines);

        Scanner sc = new Scanner (System.in);

        String input = sc.nextLine();
        while (!input.equals("Over")){

            String[] split = input.split("\\s+");
            switch (split[0]){
                case "Hire":
                    System.out.println(machinesManager.hirePilot(split[1]));
                    break;
                case "ManufactureTank":
                    System.out.println(machinesManager.manufactureTank(split[1], Double.parseDouble(split[2]), Double.parseDouble(split[3])));
                    break;
                case "ManufactureFighter":
                    System.out.println(machinesManager.manufactureFighter(split[1], Double.parseDouble(split[2]), Double.parseDouble(split[3])));
                    break;
                case "Engage":
                    System.out.println(machinesManager.engageMachine(split[1], split[2]));
                    break;
                case "Attack":
                    System.out.println(machinesManager.attackMachines(split[1], split[2]));
                    break;
                case "AggressiveMode":
                    System.out.println(machinesManager.toggleFighterAggressiveMode(split[1]));
                    break;
                case "DefenseMode":
                    System.out.println(machinesManager.toggleTankDefenseMode(split[1]));
                    break;
                case "Report":
                    System.out.println(machinesManager.pilotReport(split[1]));
                    break;
            }

            input = sc.nextLine();
        }

    }
}
