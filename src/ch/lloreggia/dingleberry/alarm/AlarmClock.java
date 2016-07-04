package ch.lloreggia.dingleberry.alarm;

import ch.lloreggia.dingleberry.io.sound.SoundPlayer;

import java.util.ArrayList;
import java.util.List;

public class AlarmClock {
    private List<Alarm> _alarms;
    private SoundPlayer _soundPlayer;

    public AlarmClock() {
        _alarms = new ArrayList<Alarm>();
        _soundPlayer = new SoundPlayer();
    }

    public void addAlarm(Alarm alarm) {
        alarm.onExecuted().addHandler(a -> onAlarmExecuted(a));
        _alarms.add(alarm);
    }

    public String getState() {
        String result = "";
        for (Alarm alarm : _alarms) {

        }

        return result;
    }

    public void setSensorState(boolean state) {

    }

    public void setState() {

    }

    private void onAlarmExecuted(Alarm alarm) {

    }
}
