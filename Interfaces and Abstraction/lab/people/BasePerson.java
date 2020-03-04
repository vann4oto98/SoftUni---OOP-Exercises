package people;

public abstract class BasePerson {
    private String name;

    protected BasePerson(String name) {
        this.setName(name);
    }

    public String getName(){
        return this.name;
    }

    private void setName( String name){
        this.name = name;
    }
}
