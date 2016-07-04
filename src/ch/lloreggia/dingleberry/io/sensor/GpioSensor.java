package ch.lloreggia.dingleberry.io.sensor;

import ch.lloreggia.dingleberry.infrastructure.Event;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class GpioSensor {
    private final Event<Boolean> _sensorStateChanged;

    public GpioSensor(int pinNumber) {
        _sensorStateChanged = new Event<>();

        final GpioController gpioController = GpioFactory.getInstance();
        final Pin pin = RaspiPin.getPinByName("GPIO " + pinNumber);
        final GpioPinDigitalInput inputPin = gpioController.provisionDigitalInputPin(pin);

        inputPin.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent e) {
                boolean state = e.getState().isHigh();
                _sensorStateChanged.execute(state);
            }
        });
    }

    public Event<Boolean> onSensorStateChanged() {
        return _sensorStateChanged;
    }
}
