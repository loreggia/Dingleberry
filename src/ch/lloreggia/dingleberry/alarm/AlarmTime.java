package ch.lloreggia.dingleberry.alarm;

public class AlarmTime {
    private int _hour;
    private int _minute;
    private int _second;

    public AlarmTime(int hour, int minute, int second) {
        _hour = hour;
        _minute = minute;
        _second = second;
    }

    @Override
    public AlarmTime clone() {
        return new AlarmTime(_hour, _minute, _second);
    }

    public int getHour() {
        return _hour;
    }

    public int getMinute() {
        return _minute;
    }

    public int getSecond() {
        return _second;
    }
}
