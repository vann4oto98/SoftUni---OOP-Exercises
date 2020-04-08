package spaceStation.models.astronauts;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.bags.Bag;
import spaceStation.models.bags.Backpack;

public abstract class BaseAstronaut implements Astronaut {
   private String name;
   private double oxygen;
   private Bag bag;
   private static final int OXYGEN_INTAKE = 10;
   private static final String NO_ITEMS = "none";

   protected BaseAstronaut(String name, Double oxygen){
        this.setName(name);
        this.setOxygen(oxygen);
        this.bag = new Backpack();
   }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name){
       if (name == null || name.trim().isEmpty()){
           throw new NullPointerException(ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY);
       }
       this.name = name;
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    private void setOxygen(Double oxygen){
       if (oxygen < 0){
           throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
       }
       this.oxygen = oxygen;
    }

    protected void decreaseOxygen(int oxygen) {
       this.oxygen -= oxygen;
       if (this.oxygen < 0) {
           this.oxygen = 0;
       }
    }


    @Override
    public Bag getBag() {
        return this.bag;
    }

    @Override
    public boolean canBreathe(){
      return this.oxygen > 0;
    }

    @Override
    public void breath() {
           this.oxygen -= OXYGEN_INTAKE;
           if (this.oxygen < 0) {
               this.oxygen = 0;
           }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_NAME, this.name));
        sb.append(System.lineSeparator());
        sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_OXYGEN, this.oxygen));
        sb.append(System.lineSeparator());

        if (this.bag.getItems().size() == 0){
            sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS, NO_ITEMS));
        } else {
            String items = String.join(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, this.bag.getItems());
            sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS, items));
        }
        return sb.toString();
    }
}
