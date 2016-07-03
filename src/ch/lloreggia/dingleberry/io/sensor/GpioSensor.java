package ch.lloreggia.dingleberry.io.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

public class GpioSensor {
    public GpioSensor() {
        final GpioController gpioController = GpioFactory.getInstance();
    }
}
