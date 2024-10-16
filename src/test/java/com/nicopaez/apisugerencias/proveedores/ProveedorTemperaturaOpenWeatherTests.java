package com.nicopaez.apisugerencias.proveedores;

import com.nicopaez.apisugerencias.dominio.ProveedorTemperatura;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProveedorTemperaturaOpenWeatherTests {

    @Test @Disabled
    public void deberiaLlamarAOpenWeatherYDevolverLaTemperatura() {
        String openweatherBaseUrl =  "http://api.openweathermap.org/data/2.5/weather"; //"http://localhost:8081/weather/";
        ProveedorTemperatura proveedorTemperatura = new ProveedorTemperaturaOpenWeather(openweatherBaseUrl);
        Integer temperatura = proveedorTemperatura.getTemperaturaEnCelcius();
        assertThat(temperatura).isNotNull();
    }
}
