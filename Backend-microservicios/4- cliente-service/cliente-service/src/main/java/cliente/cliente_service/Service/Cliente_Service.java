package cliente.cliente_service.Service;

import cliente.cliente_service.Entity.Cliente_Entity;
import cliente.cliente_service.Repository.Cliente_Repository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Cliente_Service {

    @Autowired
    Cliente_Repository cliente_repository;


    public Cliente_Entity getClienteById(int id) {
        return cliente_repository.findById(id);
    }

    public Cliente_Entity getClienteByRut(int rut) {
        return cliente_repository.findByRut(rut);
    }

    @Transactional
    public Cliente_Entity createCliente(Cliente_Entity cliente) {
        return cliente_repository.save(cliente);
    }


    public int LoginCliente(int rut, String password) {
        Cliente_Entity cliente = getClienteByRut(rut);

        if(password.equals(cliente.getContrasenia()) && cliente.getRut() == rut) {
            return cliente.getId_cliente();
        }
        else{
            return 0;
        }
    }
}
