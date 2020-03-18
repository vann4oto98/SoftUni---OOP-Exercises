package vehicle;

import java.text.DecimalFormat;

public class Bus extends Vehicle {

    private final double SUMMERCONSUMPTION = 1.4;

    protected Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    public void drive(Double distance, String...status) {
        if (status.length > 0){
            if (this.getFuelQuantity() >= this.getFuelConsumption() * distance){
                System.out.printf("Bus travelled %s km%n", new DecimalFormat("#.##").format(distance));
                this.setFuelQuantity(this.getFuelQuantity() - this.getFuelConsumption()*distance);
            } else {
                System.out.println("Bus needs refueling");
            }
            return;
        }
        if (this.getFuelQuantity() >= (this.getFuelConsumption()+SUMMERCONSUMPTION) * distance){
            System.out.printf("Bus travelled %s km%n", new DecimalFormat("#.##").format(distance));
            this.setFuelQuantity(this.getFuelQuantity() - (this.getFuelConsumption()+SUMMERCONSUMPTION) * distance);
        } else {
            System.out.println("Bus needs refueling");
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
