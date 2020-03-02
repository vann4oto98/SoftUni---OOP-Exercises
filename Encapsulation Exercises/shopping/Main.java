package shopping;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        Map<String, Person> people = new LinkedHashMap<>();
        Map<String, Product> products = new HashMap<>();
        try {
            String[] infoForPeople = sc.nextLine().split(";");
            for (int i = 0; i < infoForPeople.length; i++) {
                String[] info = infoForPeople[i].split("=");
                Person person = new Person(info[0], Double.parseDouble(info[1]));
                people.put(info[0], person);
            }

            String[] infoForProducts = sc.nextLine().split(";");
            for (int i = 0; i < infoForProducts.length; i++) {
                String[] info = infoForProducts[i].split("=");
                Product product = new Product(info[0], Double.parseDouble(info[1]));
                products.put(info[0], product);
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String input = sc.nextLine();
        while (!input.equals("END")){
            String[] personAndProduct = input.split("\\s+");
            String personName = personAndProduct[0];
            String productName = personAndProduct[1];

            try {
                people.get(personName)
                        .buyProduct(
                                products.get(productName));
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

            input = sc.nextLine();
        }

        people.values().forEach(System.out::println);
    }

}
