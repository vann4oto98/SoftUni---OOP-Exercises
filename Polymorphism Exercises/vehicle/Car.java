package vehicle;

import java.text.DecimalFormat;

public class Car extends Vehicle {

 private final double SUMMERCONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    public void drive(Double distance, String...status) {
        if (this.getFuelQuantity() >= (this.getFuelConsumption()+SUMMERCONSUMPTION) * distance){
            System.out.printf("Car travelled %s km%n", new DecimalFormat("#.##").format(distance));
            this.setFuelQuantity(this.getFuelQuantity() - (this.getFuelConsumption()+SUMMERCONSUMPTION) * distance);
        } else {
            System.out.println("Car needs refueling");
        }
    }

    @Override
    public void refuel(Double liters) {
        if (liters <= 0){
            System.out.println("Fuel must be a positive number");
            return;
        }
        this.setFuelQuantity(this.getFuelQuantity() + liters);
    }

}
