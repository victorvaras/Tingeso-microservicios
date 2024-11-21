package tipo_prestamo.tipo_prestamo_services.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tipo_prestamo.tipo_prestamo_services.Entity.Tipo_Prestamo_Entity;

@Repository
public interface Tipo_Prestamo_Repository extends JpaRepository<Tipo_Prestamo_Entity,Integer> {
}
