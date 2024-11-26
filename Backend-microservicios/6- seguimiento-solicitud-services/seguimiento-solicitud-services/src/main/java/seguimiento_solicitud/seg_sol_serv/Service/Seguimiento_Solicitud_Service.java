package seguimiento_solicitud.seg_sol_serv.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguimiento_solicitud.seg_sol_serv.Entity.Seguimiento_Solicitud_Entity;
import seguimiento_solicitud.seg_sol_serv.Repository.Seguimiento_Solicitud_Repository;

@Service
public class Seguimiento_Solicitud_Service {

    @Autowired
    Seguimiento_Solicitud_Repository seguimiento_Solicitud_Repository;

    public Seguimiento_Solicitud_Entity getSeguimiento_Solicitud(int id){
        return seguimiento_Solicitud_Repository.findById(id).get();
    }
}
