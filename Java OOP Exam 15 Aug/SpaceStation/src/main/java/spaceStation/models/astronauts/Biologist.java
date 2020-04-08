package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {
    private static double INITIAL_OXYGEN = 70;
    private static final int OXYGEN_INTAKE = 5;

    public Biologist(String name) {
        super(name, INITIAL_OXYGEN);
    }

   @Override
    public void breath(){
            this.decreaseOxygen(OXYGEN_INTAKE);
   }
}
