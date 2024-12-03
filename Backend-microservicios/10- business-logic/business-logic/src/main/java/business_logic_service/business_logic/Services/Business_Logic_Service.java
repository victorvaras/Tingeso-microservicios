package business_logic_service.business_logic.Services;

import business_logic_service.business_logic.Models.Evaluacion_Credito_Entity;
import business_logic_service.business_logic.Models.Solicitud_Credito_Entity;
import business_logic_service.business_logic.Models.Tipo_Prestamo_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Business_Logic_Service {


    @Autowired
    RestTemplate restTemplate;

    //Validacion de Cuota ingreso
    public boolean validate_R1(int ingresos_cliente, int id_solicitud_Credito){

        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);
        int monto_deseado = solicitud.getMonto_deseado();

        Tipo_Prestamo_Entity tipoPrestamo =restTemplate.getForObject("http://tipo-prestamo-service/tipo_prestamo/" + solicitud.getId_Tipo_Prestamo(), Tipo_Prestamo_Entity.class);

        double tasa_anual = tipoPrestamo.getTasa_anual();

        double tasa_mensual = tasa_anual/12/100;
        int plazo = solicitud.getPlazo_deseado() * 12;
        

        int cuota_mensual = (int) ((monto_deseado * tasa_mensual * Math.pow((1 + tasa_mensual), plazo)) /
                (Math.pow((1 + tasa_mensual), plazo) - 1));

        solicitud.setCuota_mensual(cuota_mensual);

        restTemplate.put("http://solicitud-credito-service/solicitud_credito/", solicitud);

        Evaluacion_Credito_Entity evaluacion = restTemplate.getForObject("http://evaluacion-credito-service/evaluacion_credito/"+ id_solicitud_Credito, Evaluacion_Credito_Entity.class);
        int relacion =  ((cuota_mensual * 100) / ingresos_cliente);


        if(relacion <= 35){
            evaluacion.setR1(true);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return true;
        }else{
            evaluacion.setR1(false);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return false;
        }
    }

}
