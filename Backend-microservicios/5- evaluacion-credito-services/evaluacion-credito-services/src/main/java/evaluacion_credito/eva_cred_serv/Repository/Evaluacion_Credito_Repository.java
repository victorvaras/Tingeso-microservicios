package evaluacion_credito.evaluacion_credito_services.Repository;

import evaluacion_credito.evaluacion_credito_services.Entity.Evaluacion_Credito_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Evaluacion_Credito_Repository extends JpaRepository<Evaluacion_Credito_Entity, Integer> {
}
