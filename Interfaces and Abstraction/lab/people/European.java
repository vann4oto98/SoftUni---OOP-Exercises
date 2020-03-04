package people;

public class European extends BasePerson implements Person {

    public European(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String sayHello() {
        return "Hello";
    }
}
