package tipo_prestamo.tipo_prestamo_services.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tipo_prestamo.tipo_prestamo_services.Entity.Tipo_Prestamo_Entity;
import tipo_prestamo.tipo_prestamo_services.Services.Tipo_Prestamo_Service;

@RestController
@RequestMapping("/tipo_prestamo/")
public class Tipo_Prestamo_Controller {

    @Autowired
    private Tipo_Prestamo_Service tipo_prestamo_service;


    @GetMapping("{id}")
    public ResponseEntity<Tipo_Prestamo_Entity> getTipoPrestamoById(@PathVariable int id) {
        Tipo_Prestamo_Entity tipo = tipo_prestamo_service.getTipo_prestamo(id);
        System.out.println("prueba");
        return ResponseEntity.ok(tipo);
    }
}
