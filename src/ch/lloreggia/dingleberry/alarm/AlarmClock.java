package ch.lloreggia.dingleberry.alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmClock {
    private List<Alarm> _alarms;

    public AlarmClock() {
        _alarms = new ArrayList<Alarm>();
    }

    public void addAlarm(Alarm alarm) {
        _alarms.add(alarm);
    }

    public String getState() {
        String result = "";
        for (Alarm alarm : _alarms) {

        }

        return result;
    }

    public void setState() {

    }
}
