package vehicle;

import java.text.DecimalFormat;

public class Truck extends Vehicle {

    private final double SUMMERCONSUMPTION = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    public void drive(Double distance, String...status) {
        if (this.getFuelQuantity() >= (this.getFuelConsumption()+SUMMERCONSUMPTION) * distance){
            System.out.printf("Truck travelled %s km%n", new DecimalFormat("#.##").format(distance));
            this.setFuelQuantity(this.getFuelQuantity() - (this.getFuelConsumption()+SUMMERCONSUMPTION) * distance);
        } else {
            System.out.println("Truck needs refueling");
        }
    }

    @Override
    public void refuel(Double liters) {
        if (liters <= 0){
            System.out.println("Fuel must be a positive number");
            return;
        }
        liters *= 0.95;
        this.setFuelQuantity(this.getFuelQuantity() + liters);
    }


}
