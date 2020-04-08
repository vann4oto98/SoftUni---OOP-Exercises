package spaceStation.models.astronauts;

public class Geodesist extends BaseAstronaut {
    private static double INITIAL_OXYGEN = 50;

    public Geodesist(String name) {
        super(name, INITIAL_OXYGEN);
    }
}
