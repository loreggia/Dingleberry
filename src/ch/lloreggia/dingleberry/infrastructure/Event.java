package ch.lloreggia.dingleberry.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class Event<T> {
    private List<EventHandler<T>> _registrations;

    public Event() {
        _registrations = new ArrayList<EventHandler<T>>();
    }

    public void addHandler(EventHandler<T> handler) {
        _registrations.add(handler);
    }

    public void execute(T param) {
        for (EventHandler<T> handler : _registrations) {
            handler.execute(param);
        }
    }
}
