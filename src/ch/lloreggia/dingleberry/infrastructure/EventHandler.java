package ch.lloreggia.dingleberry.infrastructure;

public interface EventHandler<T> {
    void execute(T param);
}
