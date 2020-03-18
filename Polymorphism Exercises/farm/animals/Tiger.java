package farm.animals;

import farm.food.Food;

public class Tiger extends Felime {
    private String livingRegion; // possible later use

    public Tiger(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight, livingRegion);
        this.livingRegion = livingRegion;
    }


    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {
        if (food.getClass().getSimpleName().equals("Vegetable")){
            System.out.println("Tigers are not eating that type of food!");
        } else {
            super.setFoodEaten(this.getFoodEaten() + food.getQuantity());
        }
    }
}
