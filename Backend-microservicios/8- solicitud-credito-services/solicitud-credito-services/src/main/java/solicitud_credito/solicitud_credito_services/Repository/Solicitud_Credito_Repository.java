package solicitud_credito.solicitud_credito_services.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solicitud_credito.solicitud_credito_services.Entity.Solicitud_Credito_Entity;

@Repository
public interface Solicitud_Credito_Repository extends JpaRepository<Solicitud_Credito_Entity,Integer> {

    public Solicitud_Credito_Entity findById(int id);
}
