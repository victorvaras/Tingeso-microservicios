package seguimiento_solicitud.seg_sol_serv.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seguimiento_solicitud.seg_sol_serv.Entity.Seguimiento_Solicitud_Entity;
import seguimiento_solicitud.seg_sol_serv.Service.Seguimiento_Solicitud_Service;

@RestController
@RequestMapping("/seguimiento_solicitud/")
public class Seguimiento_Solicitud_Controller {

    @Autowired
    private Seguimiento_Solicitud_Service seguimiento_Solicitud_Service;

    @GetMapping("{id}")
    public ResponseEntity<Seguimiento_Solicitud_Entity> obtenerSeguimiento_Solicitud(@PathVariable int id) {
        Seguimiento_Solicitud_Entity seguimieto = seguimiento_Solicitud_Service.getSeguimiento_Solicitud(id);
        return ResponseEntity.ok(seguimieto);
    }
}
