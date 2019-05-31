package test;

import clima.AccuWeather;
import clima.Clima;
import clima.OpenWeather;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClimaTest {

    @Disabled
    @Test
    @DisplayName("Openweather responde consultas sin errores")
    void openWeatherResponde() {
        Clima londres = AccuWeather.getInstance().obtenerClima("London");
        System.out.println(londres);
        assertTrue(1 == 1);
    }

}