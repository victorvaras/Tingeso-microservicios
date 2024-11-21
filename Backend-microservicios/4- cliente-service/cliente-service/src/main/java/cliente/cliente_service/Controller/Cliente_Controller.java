package cliente.cliente_service.Controller;

import cliente.cliente_service.Entity.Cliente_Entity;
import cliente.cliente_service.Service.Cliente_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/cliente/")
public class Cliente_Controller {

    @Autowired
    Cliente_Service cliente_service;

    @PostMapping("/nuevo")
    public ResponseEntity<Cliente_Entity> newCliente(
            @RequestParam("rut") int rut,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("contrasenia") String contrasenia,
            @RequestParam("identificacion") MultipartFile identificacion,
            @RequestParam("comprobante_ingresos") MultipartFile comprobanteIngresos)
    {

        // Crear nueva instancia de Cliente_Entity
        Cliente_Entity nuevoCliente = new Cliente_Entity();
        nuevoCliente.setRut(rut);
        nuevoCliente.setNombre(nombre);
        nuevoCliente.setApellido(apellido);
        nuevoCliente.setContrasenia(contrasenia);


        try {
            // Asegurarse de que el archivo no esté vacío antes de leer los bytes
            if (!identificacion.isEmpty()) {
                nuevoCliente.setIdentificacion(identificacion.getBytes());
            } else {
                throw new IOException("El archivo de identificación está vacío");
            }

            if (!comprobanteIngresos.isEmpty()) {
                nuevoCliente.setComprobante_ingresos(comprobanteIngresos.getBytes());
            } else {
                throw new IOException("El archivo de comprobante de ingresos está vacío");
            }
        } catch (IOException e) {
            // Manejo del error en la lectura de archivos
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        // Guardar cliente en la base de datos
        Cliente_Entity nuevo = cliente_service.createCliente(nuevoCliente);
        return ResponseEntity.ok(nuevo);
    }



    @GetMapping("{id}")
    public ResponseEntity<Cliente_Entity> getClienteById(@PathVariable int id) {

        Cliente_Entity cliente = cliente_service.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("login")
    public ResponseEntity<Integer> getClienteByLogin(@RequestBody Cliente_Entity cliente) {

        int login = cliente_service.LoginCliente(cliente.getRut(), cliente.getContrasenia());
        return ResponseEntity.ok(login);
    }
}
