package cliente.cliente_service.Repository;

import cliente.cliente_service.Entity.Cliente_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cliente_Repository extends JpaRepository<Cliente_Entity, Integer> {
    public Cliente_Entity findByRut(int rut);
    public Cliente_Entity findById(int id);
}
