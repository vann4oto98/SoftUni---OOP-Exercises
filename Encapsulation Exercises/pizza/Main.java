package pizza;

public class Main {
    public static void main(String[] args) {

        Pizza pizza = new Pizza("Meatless", 2);
        Dough dough = new Dough("Wholegrain", "Crispy", 100);
        Topping topping1 = new Topping("Veggies", 50);
        Topping topping2 = new Topping("Cheese", 50);

        pizza.setDough(dough);
        pizza.addTopping(topping1);
        pizza.addTopping(topping2);
        System.out.println(pizza);
    }
}
