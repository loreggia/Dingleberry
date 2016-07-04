package ch.lloreggia.dingleberry;

import ch.lloreggia.dingleberry.alarm.AlarmClock;
import ch.lloreggia.dingleberry.api.ApiServer;
import ch.lloreggia.dingleberry.io.sensor.GpioSensor;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration();

            GpioSensor sensor = new GpioSensor(configuration.getGpioPin());
            AlarmClock alarmClock = new AlarmClock();
            sensor.onSensorStateChanged().addHandler(state -> alarmClock.setSensorState(state));

            ApiServer apiServer = new ApiServer(configuration.getServerPort());
            apiServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
