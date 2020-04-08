package entities;

import entities.interfaces.Fighter;


public class FighterImpl extends BaseMachine implements Fighter {
    private static final double INITIAL_HEALTHPOINTS = 200;
    private boolean aggressiveMode = true;
    private double attackPointsModifier = 50.0;
    private double defencePointsModifier = 25.0;

    public FighterImpl(String name, double attackPoints, double defencePoints) {
        super(name, attackPoints, defencePoints, INITIAL_HEALTHPOINTS);
        super.setAttackPoints(super.getAttackPoints() + attackPointsModifier);
        super.setDefensePoints(super.getDefensePoints() - defencePointsModifier);
    }

    @Override
    public boolean getAggressiveMode() {
        return aggressiveMode;
    }

    @Override
    public void toggleAggressiveMode() {
            if (aggressiveMode){
                this.aggressiveMode = false;
                super.setAttackPoints(super.getAttackPoints() - this.attackPointsModifier);
                super.setDefensePoints(super.getDefensePoints() + this.defencePointsModifier);
            } else {
                this.aggressiveMode = true;
                super.setAttackPoints(super.getAttackPoints() + this.attackPointsModifier);
                super.setDefensePoints(super.getDefensePoints() - this.defencePointsModifier);
            }
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append(" - ")
               .append(this.getName())
               .append(System.lineSeparator())
               .append(" *Type: Fighter")
               .append(System.lineSeparator())
               .append(" *Health: ")
               .append(this.getHealthPoints())
               .append(System.lineSeparator())
               .append(" *Attack: ")
               .append(this.getAttackPoints())
               .append(System.lineSeparator())
               .append(" *Defense: ")
               .append(this.getDefensePoints())
               .append(System.lineSeparator())
               .append(" *Targets: ");

       if (this.getTargets().isEmpty()){
           sb.append("None");
       } else {
           sb.append(String.join(", ", this.getTargets()));
       }
        String mode;
        if (getAggressiveMode()){
            mode = "ON";
        } else {
            mode = "OFF";
        }
        sb.append(System.lineSeparator())
            .append(String.format(" *Aggressive Mode(%s)", mode));
        sb.append(System.lineSeparator());
        return sb.toString().trim();
    }
}
