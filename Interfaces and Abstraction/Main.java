import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        int n = Integer.parseInt(sc.nextLine());
        Map<String, Buyer> buyers = new HashMap<>();

        for (int i = 0; i <n; i++) {
            String[] input = sc.nextLine().split("\\s+");
            if (input.length > 3){
                buyers.put(input[0], new Citizen(input[0], Integer.parseInt(input[1]), input[2], input[3]));
            } else {
                buyers.put(input[0], new Rebel(input[0], Integer.parseInt(input[1]), input[2]));
            }
        }
        String buyCommand = sc.nextLine();
        while (!buyCommand.equals("End")){
                if (buyers.containsKey(buyCommand)){
                    buyers.get(buyCommand).buyFood();
            }
            buyCommand = sc.nextLine();
        }
        int totalFood = 0;
        Buyer[] buyersArray = buyers.values().toArray(new Buyer[0]);
        for (Buyer buyer: buyersArray) {
            totalFood += buyer.getFood();
        }
        System.out.println(totalFood);

    }
}
