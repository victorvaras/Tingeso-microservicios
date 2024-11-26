package solicitud_credito.solicitud_credito_services.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import solicitud_credito.solicitud_credito_services.Entity.Solicitud_Credito_Entity;
import solicitud_credito.solicitud_credito_services.Models.Evaluacion_Credito_Entity;
import solicitud_credito.solicitud_credito_services.Repository.Solicitud_Credito_Repository;

import java.util.List;

@Service
public class Solicitud_Credito_Service {

    @Autowired
    private Solicitud_Credito_Repository solicitud_Credito_Repository;

    @Autowired
    RestTemplate restTemplate;

    public List<Solicitud_Credito_Entity> getAllSolicitud_Credito(){
        return solicitud_Credito_Repository.findAll();
    }

    public Solicitud_Credito_Entity getSolicitud_Credito(int id){
        return solicitud_Credito_Repository.findById(id);
    }


    @Transactional
    public Solicitud_Credito_Entity nuevaSolicitud_Credito(Solicitud_Credito_Entity solicitud_Credito){

        Evaluacion_Credito_Entity evaluacionCredito = restTemplate.postForObject("http://evaluacion-credito-service/evaluacion_credito/nuevo" , new Evaluacion_Credito_Entity() ,Evaluacion_Credito_Entity.class);
        int id_evaluacion_credito = evaluacionCredito.getId_evaluacion_credito();

        solicitud_Credito.setId_evaluacion_credito(id_evaluacion_credito);
        solicitud_Credito.setId_seguimiento_solicitud(1);

        return solicitud_Credito_Repository.save(solicitud_Credito);
    }



    public Solicitud_Credito_Entity updateSolicitud_Credito_SeguimientoSolicitud(int id_evaluacion_credito, int id_seguimiento_solicitud){

        if(solicitud_Credito_Repository.findById(id_evaluacion_credito) != null){
            Solicitud_Credito_Entity update = solicitud_Credito_Repository.findById(id_evaluacion_credito);
            update.setId_seguimiento_solicitud(id_seguimiento_solicitud);
            return solicitud_Credito_Repository.save(update);
        }
        else{
            return null;
        }

    }

}

