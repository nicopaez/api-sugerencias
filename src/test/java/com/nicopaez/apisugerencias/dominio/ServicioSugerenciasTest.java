package com.nicopaez.apisugerencias.dominio;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioSugerenciasTest {

    @Test
    public void cuandoTemperaturaMenorA30SurgierePolera() {
        ProveedorTemperatura provedorTemperatura = mock(ProveedorTemperatura.class);
        when(provedorTemperatura.getTemperaturaEnCelcius()).thenReturn(29);

        ServicioSugerencias sistema = new ServicioSugerencias(provedorTemperatura);

        Vestimenta vestimentaSugerida = sistema.sugerirVestimenta();

        assertThat(vestimentaSugerida).isEqualTo(Vestimenta.Polera);
    }

    @Test
    public void cuandoTemperaturaMayorIgualA30SurgiereRemera() {
        ProveedorTemperatura provedorTemperatura = mock(ProveedorTemperatura.class);
        when(provedorTemperatura.getTemperaturaEnCelcius()).thenReturn(31);
        ServicioSugerencias sistema = new ServicioSugerencias(provedorTemperatura);

        Vestimenta vestimentaSugerida = sistema.sugerirVestimenta();

        assertThat(vestimentaSugerida).isEqualTo(Vestimenta.Remera);
    }
}
