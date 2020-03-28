package domain.entities.commands;

public class EndCommand implements Command<EmptyInput> {

    @Override
    public void execute(EmptyInput input) {
        System.exit(0);
    }
}
