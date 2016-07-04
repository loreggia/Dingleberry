package ch.lloreggia.dingleberry.io.sensor;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.util.ArrayList;

public class GpioSensor {
    private final ArrayList<SensorStateHandler> _sensorStateHandlers;

    public GpioSensor(int pinNumber) {
        _sensorStateHandlers = new ArrayList<SensorStateHandler>();

        final GpioController gpioController = GpioFactory.getInstance();
        final Pin pin = RaspiPin.getPinByName("GPIO " + pinNumber);
        final GpioPinDigitalInput inputPin = gpioController.provisionDigitalInputPin(pin);
        inputPin.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent e) {
                boolean state = e.getState().isHigh();
                for (SensorStateHandler handler : _sensorStateHandlers) {
                    handler.handle(state);
                }
            }
        });
    }

    public void addSensorStateHandler(SensorStateHandler handler) {
        _sensorStateHandlers.add(handler);
    }
}
