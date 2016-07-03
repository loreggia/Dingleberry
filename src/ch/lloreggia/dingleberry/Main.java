package ch.lloreggia.dingleberry;

import ch.lloreggia.dingleberry.alarm.AlarmClock;
import ch.lloreggia.dingleberry.api.ApiServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration();

            AlarmClock alarmClock = new AlarmClock();

            ApiServer apiServer = new ApiServer(configuration.getServerPort());
            apiServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
