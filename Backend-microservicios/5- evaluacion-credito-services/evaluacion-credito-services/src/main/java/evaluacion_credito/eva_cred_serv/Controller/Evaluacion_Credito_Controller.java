package evaluacion_credito.eva_cred_serv.Controller;

import evaluacion_credito.eva_cred_serv.Entity.Evaluacion_Credito_Entity;
import evaluacion_credito.eva_cred_serv.Service.Evaluacion_Credito_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluacion_credito/")
public class Evaluacion_Credito_Controller {

    @Autowired
    Evaluacion_Credito_service evaluacion_credito_service;

    @PostMapping("nuevo")
    public ResponseEntity<Evaluacion_Credito_Entity> nuevoEvaluacionCredito() {
        Evaluacion_Credito_Entity evaluacion_credito = evaluacion_credito_service.createEvaluacion_Credito();
        return ResponseEntity.ok(evaluacion_credito);
    }



    @GetMapping("{id_solicitud}")
    public ResponseEntity<Evaluacion_Credito_Entity> getEvaluacionCredito(@PathVariable int id_solicitud) {

        Evaluacion_Credito_Entity evaluacion = evaluacion_credito_service.getEvaluacion_Credito(id_solicitud);

        return ResponseEntity.ok(evaluacion);
    }
}
