package farm.animals;

import farm.food.Food;

public class Zebra extends Mammal {


    public Zebra(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }

    @Override
    public void eat(Food food) {
        if (!food.getClass().getSimpleName().equals("Vegetable")){
            System.out.println("Zebras are not eating that type of food!");
        } else {
            super.setFoodEaten(this.getFoodEaten() + food.getQuantity());
        }
    }
}
