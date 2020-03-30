import core.ManagerControllerImpl;
import core.interfaces.ManagerController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        ManagerController managerController = new ManagerControllerImpl();

        while (true){
            String[] command = sc.nextLine().split("\\s+");
            switch (command[0]){
                case "AddPlayer":
                    managerController.addPlayer(command[1], command[2]);
                    break;
                case "AddCard":
                    managerController.addCard(command[1], command[2]);
                    break;
                case "AddPlayerCard":
                    managerController.addPlayerCard(command[1], command[2]);
                    break;
                case "Fight":
                    managerController.fight(command[1], command[2]);
                    break;
                case "Report":
                    System.out.println(managerController.report());
                    break;
                case "Exit":
                    return;
            }
        }
    }
}
