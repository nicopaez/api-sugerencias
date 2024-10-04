package com.nicopaez.apisugerencias;

import com.nicopaez.apisugerencias.dominio.ProveedorTemperatura;
import com.nicopaez.apisugerencias.dominio.ServicioSugerencias;
import com.nicopaez.apisugerencias.proveedores.ProveedorTemperaturaOpenWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@org.springframework.context.annotation.Configuration
public class ApiSugerenciasConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public ProveedorTemperaturaOpenWeather getProveedorTemperatura(){
        String openweatherUrl = environment.getProperty("openweather.baseurl");
        return new ProveedorTemperaturaOpenWeather(openweatherUrl);
    }

    @Bean
    public ServicioSugerencias getServicioSugerencias(ProveedorTemperatura proveedorTemperatura) {
        return new ServicioSugerencias(proveedorTemperatura);
    }
}
