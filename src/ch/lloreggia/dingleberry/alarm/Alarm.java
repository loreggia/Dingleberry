package ch.lloreggia.dingleberry.alarm;

import ch.lloreggia.dingleberry.infrastructure.Event;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Alarm {
    private AlarmTime _alarmTime;
    private DayOfWeek[] _days;
    private Event<Alarm> _executed;
    private String _id;
    private boolean _isEnabled;
    private String _name;
    private Timer _timer;

    public Alarm(String id, String name, AlarmTime alarmTime, DayOfWeek[] days, boolean isEnabled) {
        if (id == null) {
            _id = UUID.randomUUID().toString();
        } else {
            _id = id;
        }

        _executed = new Event<>();

        _name = name;
        _alarmTime = alarmTime;
        _days = days;
        _isEnabled = isEnabled;
        _timer = new Timer();
    }

    public AlarmTime getAlarmTime() {
        return _alarmTime;
    }

    public void setAlarmTime(AlarmTime alarmTime) {
        _alarmTime = alarmTime;
        updateTimer();
    }

    public DayOfWeek[] getDays() {
        return _days;
    }

    public void setDays(DayOfWeek[] days) {
        _days = days;
    }

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        _name = _name;
    }

    public boolean isEnabled() {
        return _isEnabled;
    }

    public Event<Alarm> onExecuted() {
        return _executed;
    }

    public void setIsEnabled(boolean isEnabled) {
        _isEnabled = isEnabled;
        updateTimer();
    }

    private void executeAlarm() {
        DayOfWeek today = LocalDateTime.now().getDayOfWeek();

        if (_days != null && Arrays.asList(_days).contains(today)) {
            _executed.execute(this);
        }
    }

    private void updateTimer() {
        _timer.cancel();
        _timer.purge();

        if (!_isEnabled) {
            return;
        }

        if (_days != null && _days.length > 0) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime start = LocalDateTime.of(
                    now.getYear(),
                    now.getMonth(),
                    now.getDayOfMonth(),
                    _alarmTime.getHour(),
                    _alarmTime.getMinute(),
                    _alarmTime.getSecond());

            if (start.compareTo(now) < 0) {
                start = start.plusDays(1);
            }

            Date startDate = Date.from(start.atZone(ZoneId.systemDefault()).toInstant());

            _timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    executeAlarm();
                }
            }, startDate, Period.ofDays(1).get(ChronoUnit.MILLIS));
        }
    }
}
