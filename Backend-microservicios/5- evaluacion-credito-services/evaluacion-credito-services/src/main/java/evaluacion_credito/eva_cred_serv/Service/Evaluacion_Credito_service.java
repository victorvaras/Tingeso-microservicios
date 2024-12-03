package evaluacion_credito.eva_cred_serv.Service;

import evaluacion_credito.eva_cred_serv.Entity.Evaluacion_Credito_Entity;
import evaluacion_credito.eva_cred_serv.Model.Solicitud_Credito_Entity;
import evaluacion_credito.eva_cred_serv.Repository.Evaluacion_Credito_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Evaluacion_Credito_service {

    @Autowired
    Evaluacion_Credito_Repository evaluacion_credito_repository;

    @Autowired
    RestTemplate restTemplate;

    public Evaluacion_Credito_Entity getEvaluacion_Credito(int id_solicitud){

        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/"+id_solicitud, Solicitud_Credito_Entity.class);
        int id = solicitud.getId_evaluacion_credito();
        return evaluacion_credito_repository.findById(id).get();
    }

    public Evaluacion_Credito_Entity createEvaluacion_Credito(){
        Evaluacion_Credito_Entity nuevo = new Evaluacion_Credito_Entity();
        return evaluacion_credito_repository.save(nuevo);
    }


    public Evaluacion_Credito_Entity updateEvaluacion_Credito(Evaluacion_Credito_Entity nuevo){
        return evaluacion_credito_repository.save(nuevo);
    }

}
