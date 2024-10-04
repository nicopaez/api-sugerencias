package com.nicopaez.apisugerencias;

import com.nicopaez.apisugerencias.dominio.ServicioSugerencias;
import com.nicopaez.apisugerencias.dominio.Vestimenta;
import com.nicopaez.apisugerencias.dtos.SugerenciaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiSugerenciasController {

    @Autowired
    public ServicioSugerencias servicioSugerencias;

    @GetMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

    @GetMapping("sugerencias")
    public ResponseEntity<SugerenciaResponse> getSugerencia(){
        Vestimenta vestimentaSurgerida = this.servicioSugerencias.sugerirVestimenta();
        SugerenciaResponse response = new SugerenciaResponse();
        response.vestimentaSugerida = vestimentaSurgerida.toString();
        return ResponseEntity.ok(response);
    }

}
