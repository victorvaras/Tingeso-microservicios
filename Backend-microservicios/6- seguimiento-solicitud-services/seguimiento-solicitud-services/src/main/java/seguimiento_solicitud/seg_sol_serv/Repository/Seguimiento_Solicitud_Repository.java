package seguimiento_solicitud.seg_sol_serv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguimiento_solicitud.seg_sol_serv.Entity.Seguimiento_Solicitud_Entity;

@Repository
public interface Seguimiento_Solicitud_Repository extends JpaRepository<Seguimiento_Solicitud_Entity,Integer> {
}
