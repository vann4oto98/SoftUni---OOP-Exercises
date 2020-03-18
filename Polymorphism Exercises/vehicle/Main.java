package vehicle;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        String[] carInput = sc.nextLine().split("\\s+");
        Vehicle car = new Car(Double.parseDouble(carInput[1]), Double.parseDouble(carInput[2]), Double.parseDouble(carInput[3]));
        String[] truckInput = sc.nextLine().split("\\s+");
        Vehicle truck = new Truck(Double.parseDouble(truckInput[1]), Double.parseDouble(truckInput[2]), Double.parseDouble(truckInput[3]));
        String[] busInput = sc.nextLine().split("\\s+");
        Vehicle bus = new Bus(Double.parseDouble(busInput[1]), Double.parseDouble(busInput[2]), Double.parseDouble(busInput[3]));

        int lines = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < lines; i++) {
            String[] info = sc.nextLine().split("\\s+");
            double value = Double.parseDouble(info[2]);
            if (info[0].equals("Drive")){
                if (info[1].equals("Car")){
                    car.drive(value);
                } else if (info[1].equals("Truck")){
                    truck.drive(value);
                } else {
                    bus.drive(value);
                }
            } else if (info[0].equals("Refuel")){
                if (info[1].equals("Car")){
                    car.refuel(value);
                } else if (info[1].equals("Truck")){
                    truck.refuel(value);
                } else {
                    bus.refuel(value);
                }
            } else {
                bus.drive(value, "Empty");
            }
        }
        System.out.println(car);
        System.out.println(truck);
        System.out.println(bus);
    }
}
