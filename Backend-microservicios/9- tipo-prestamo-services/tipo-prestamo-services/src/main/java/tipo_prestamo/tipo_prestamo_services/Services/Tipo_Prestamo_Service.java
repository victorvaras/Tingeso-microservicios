package tipo_prestamo.tipo_prestamo_services.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tipo_prestamo.tipo_prestamo_services.Entity.Tipo_Prestamo_Entity;
import tipo_prestamo.tipo_prestamo_services.Repository.Tipo_Prestamo_Repository;

@Service
public class Tipo_Prestamo_Service {

    @Autowired
    Tipo_Prestamo_Repository tipo_prestamo_repository;

    public Tipo_Prestamo_Entity getTipo_prestamo(int id) {
        return tipo_prestamo_repository.findById(id).get();
    }

}
