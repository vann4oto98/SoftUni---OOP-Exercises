package farm;

import farm.animals.*;
import farm.food.Food;
import farm.food.Meat;
import farm.food.Vegetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //{AnimalType} {AnimalName} {AnimalWeight} {AnimalLivingRegion} [{CatBreed} = Only if its cat]
        // Mammal cat = new Cat(info[0], info[1], Double.parseDouble(info[2]), info[3], info[4]);
        //animal = new Mouse(info[0], info[1], Double.parseDouble(info[2]), info[3]);
        List<Mammal> mammals = new ArrayList<>();
        String input = sc.nextLine();
        int i = 0;
        int animalCount = 0;
        while (!input.equals("End")) {
            String[] info = input.split("\\s+");
            if (i % 2 == 0){
                Mammal animal;
                if (info.length == 5){
                   animal = new Cat(info[0], info[1], Double.parseDouble(info[2]), info[3], info[4]);
                } else {
                    if (info[0].equals("Mouse")){
                        animal = new Mouse(info[0], info[1], Double.parseDouble(info[2]), info[3]);
                    } else if (info[0].equals("Zebra")){
                        animal = new Zebra(info[0], info[1], Double.parseDouble(info[2]), info[3]);
                    } else {
                        animal = new Tiger(info[0], info[1], Double.parseDouble(info[2]), info[3]);
                    }
                }
                mammals.add(animal);
                animalCount++;
            } else {
                Food food;
                if (info[0].equals("Vegetable")){
                    food = new Vegetable(Integer.parseInt(info[1]));
                } else {
                    food = new Meat(Integer.parseInt(info[1]));
                }
                mammals.get(animalCount-1).makeSound();
                mammals.get(animalCount-1).eat(food);
            }
            i++;
            input = sc.nextLine();
        }
        for (Mammal mammal: mammals) {
            System.out.println(mammal);
        }
    }
}
