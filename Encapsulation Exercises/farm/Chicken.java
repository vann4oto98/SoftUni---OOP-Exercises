package farm;

public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name.length() < 1){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        if (age<0 || age > 15){
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }

    public double productPerDay(){
        return this.calculateProductPerDay();
    }

    private double calculateProductPerDay(){
        int age = this.getAge();
        double eggs = 0;
            if (age < 6){
                eggs = 2;
            }
            if (age>=6 && age<12){
                eggs =  1;
            }
            if (age>=12){
               eggs =  0.75;
            }
        return eggs;
    }

    @Override
    public String toString() {
        return String.format("Chicken %s (%d) can produce %.2f eggs per day.", this.getName(), this.getAge(), this.productPerDay());
    }
}
