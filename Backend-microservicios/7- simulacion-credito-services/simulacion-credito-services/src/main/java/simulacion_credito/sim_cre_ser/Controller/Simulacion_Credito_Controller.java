package simulacion_credito.sim_cre_ser.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simulacion_credito.sim_cre_ser.Entity.Simulacion_Credito_Entity;
import simulacion_credito.sim_cre_ser.Services.Simulacion_Credito_Service;

@RestController
@RequestMapping("/simulacion_credito/")
public class Simulacion_Credito_Controller {

    @Autowired
    Simulacion_Credito_Service simulacion_credito_service;

    @PostMapping("nuevo")
    public ResponseEntity<Simulacion_Credito_Entity> newSimulacionCredito(@RequestBody Simulacion_Credito_Entity simulacion_credito) {
        System.out.println("hola" + simulacion_credito);
        Simulacion_Credito_Entity nuevo = simulacion_credito_service.newSimulacionCredito(simulacion_credito);
        return ResponseEntity.ok(nuevo);
    }
}
