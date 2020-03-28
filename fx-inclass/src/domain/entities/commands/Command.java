package domain.entities.commands;

public interface Command<T extends EmptyInput> {
    void execute(T  input);
}
