package simulacion_credito.sim_cre_ser.Repopsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simulacion_credito.sim_cre_ser.Entity.Simulacion_Credito_Entity;

@Repository
public interface Simulacion_Credito_Repository extends JpaRepository<Simulacion_Credito_Entity,Integer> {
}
