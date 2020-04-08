package entities;

import entities.interfaces.Tank;

public class TankImpl extends BaseMachine implements Tank {
    private static final double INITIAL_HEALTH = 100;
    private boolean defenceMode = true;
    private double attackPointsModifier = 40.0;
    private double defencePointsModifier = 30.0;

    public TankImpl(String name, double attackPoints, double defencePoints) {
        super(name, attackPoints, defencePoints, INITIAL_HEALTH);
        // because tanks start on defence mode // defence mode true
        super.setDefensePoints(super.getDefensePoints() + defencePointsModifier);
        super.setAttackPoints(super.getAttackPoints() - attackPointsModifier);
    }

    @Override
    public boolean getDefenseMode() {
        return this.defenceMode;
    }

    @Override
    public void toggleDefenseMode() {
        if (defenceMode){
            this.defenceMode = false;
            super.setAttackPoints(super.getAttackPoints() + this.attackPointsModifier);
            super.setDefensePoints(super.getDefensePoints() - this.defencePointsModifier);
        } else {
            this.defenceMode = true;
            super.setAttackPoints(super.getAttackPoints() - this.attackPointsModifier);
            super.setDefensePoints(super.getDefensePoints() + this.defencePointsModifier);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" - ")
                .append(this.getName())
                .append(System.lineSeparator())
                .append(" *Type: Tank")
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
        if (getDefenseMode()){
            mode = "ON";
        } else {
            mode = "OFF";
        }
        sb.append(System.lineSeparator())
                .append(String.format(" *Defence Mode(%s)", mode));
        sb.append(System.lineSeparator());
        return sb.toString().trim();
    }
}
