package pizza;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight){
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {
        if (!flourType.equals("White") && !flourType.equals("Wholegrain")){
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        if (!bakingTechnique.equals("Crispy") && !bakingTechnique.equals("Chewy") && !bakingTechnique.equals("Homemade")){
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight>200){
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }
    public double calculateCalories(){
        double calories = 2 * getWeight();

        if (getFlourType().equals("White")){
            calories *= 1.5;
        }

        if (getBakingTechnique().equals("Crispy")){
            calories *= 0.9;
        } else if (getBakingTechnique().equals("Chewy")){
            calories *= 1.1;
        }

        return calories;
    }




    public String getFlourType() {
        return flourType;
    }

    public String getBakingTechnique() {
        return bakingTechnique;
    }

    public double getWeight() {
        return weight;
    }
}
