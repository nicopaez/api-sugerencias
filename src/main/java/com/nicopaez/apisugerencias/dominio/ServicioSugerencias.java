package com.nicopaez.apisugerencias.dominio;

public class ServicioSugerencias {

    private final ProveedorTemperatura proveedorTemperatura;

    public ServicioSugerencias(ProveedorTemperatura provedorTemperatura) {
        this.proveedorTemperatura = provedorTemperatura;
    }

    public Vestimenta sugerirVestimenta() {
        if (this.proveedorTemperatura.getTemperatura() >= 30)
            return Vestimenta.Remera;
        return Vestimenta.Polera;
    }
}
