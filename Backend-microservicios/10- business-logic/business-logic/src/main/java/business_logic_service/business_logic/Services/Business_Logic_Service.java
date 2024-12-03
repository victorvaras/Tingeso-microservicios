package business_logic_service.business_logic.Services;

import business_logic_service.business_logic.Models.Evaluacion_Credito_Entity;
import business_logic_service.business_logic.Models.Solicitud_Credito_Entity;
import business_logic_service.business_logic.Models.Tipo_Prestamo_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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

    public boolean validate_R2(int id_solicitud_Credito, boolean requisito){
        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);
        Evaluacion_Credito_Entity evaluacion = restTemplate.getForObject("http://evaluacion-credito-service/evaluacion_credito/"+ id_solicitud_Credito, Evaluacion_Credito_Entity.class);

        evaluacion.setR2(requisito);
        restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);

        if(requisito){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean validate_R3(int id_solicitud_Credito, boolean requisito){
        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);
        Evaluacion_Credito_Entity evaluacion = restTemplate.getForObject("http://evaluacion-credito-service/evaluacion_credito/"+ id_solicitud_Credito, Evaluacion_Credito_Entity.class);

        evaluacion.setR3(requisito);
        restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);

        if(requisito){
            return true;
        }
        else{
            return false;
        }
    }


    public boolean validate_R4(int id_solicitud_Credito,int ingresos_cliente ,int deuda_total){
        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);
        Evaluacion_Credito_Entity evaluacion = restTemplate.getForObject("http://evaluacion-credito-service/evaluacion_credito/"+ id_solicitud_Credito, Evaluacion_Credito_Entity.class);

        int valor_cuota = solicitud.getCuota_mensual();

        int relacion = ((deuda_total + valor_cuota) * 100) / ingresos_cliente;
        if(relacion <= 50){
            evaluacion.setR4(true);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return true;
        }else{
            evaluacion.setR4(false);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return false;
        }
    }


    public boolean validate_R5(int id_solicitud_Credito, int valor_propiedad){
        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);
        Tipo_Prestamo_Entity tipoPrestamo =restTemplate.getForObject("http://tipo-prestamo-service/tipo_prestamo/" + solicitud.getId_Tipo_Prestamo(), Tipo_Prestamo_Entity.class);
        Evaluacion_Credito_Entity evaluacion = restTemplate.getForObject("http://evaluacion-credito-service/evaluacion_credito/"+ id_solicitud_Credito, Evaluacion_Credito_Entity.class);

        int monto_deseado = solicitud.getMonto_deseado();
        int porcentaje_maximo_financiamiento = tipoPrestamo.getPorcentaje_maximo_financiamiento();

        int porcentaje_financiamiento = (monto_deseado * 100) / valor_propiedad;

        if(porcentaje_financiamiento <= porcentaje_maximo_financiamiento){
            evaluacion.setR5(true);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return true;
        }
        else{
            evaluacion.setR5(false);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return false;
        }
    }

    public boolean validate_R6(int id_solicitud_Credito,int edad_cliente){
        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);
        Evaluacion_Credito_Entity evaluacion = restTemplate.getForObject("http://evaluacion-credito-service/evaluacion_credito/"+ id_solicitud_Credito, Evaluacion_Credito_Entity.class);

        int plazo_deseado = solicitud.getPlazo_deseado();

        int edad_final = edad_cliente + plazo_deseado;
        if(edad_final <=70){
            evaluacion.setR6(true);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return true;
        }
        else{
            evaluacion.setR6(false);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return false;
        }
    }

    public String validate_R7(int id_solicitud_Credito){
        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);
        Evaluacion_Credito_Entity evaluacion = restTemplate.getForObject("http://evaluacion-credito-service/evaluacion_credito/"+ id_solicitud_Credito, Evaluacion_Credito_Entity.class);


        int contador = 0;
        if (evaluacion.isR1()){
            contador++;
        }
        if (evaluacion.isR2()){
            contador++;
        }
        if (evaluacion.isR3()){
            contador++;
        }
        if (evaluacion.isR4()){
            contador++;
        }
        if (evaluacion.isR5()){
            contador++;
        }

        if (contador == 5){
            evaluacion.setR7("solida");
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return "solida";
        }
        else if (contador == 4 || contador == 3) {
            evaluacion.setR7("moderada");
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return "moderada";
        }
        else {
            evaluacion.setR7("insuficiente");
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return "insuficiente";
        }
    }



    public boolean validate_R71(int id_solicitud_Credito, int saldo_cuenta){

        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);
        Evaluacion_Credito_Entity evaluacion = restTemplate.getForObject("http://evaluacion-credito-service/evaluacion_credito/"+ id_solicitud_Credito, Evaluacion_Credito_Entity.class);

        int monto_deseado = solicitud.getMonto_deseado();

        int relacion = (saldo_cuenta * 100) / monto_deseado;
        if(relacion >= 10){
            evaluacion.setR71(true);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return true;
        }
        else {
            evaluacion.setR71(false);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return false;
        }
    }


    public boolean validate_R72(int id_solicitud_Credito, int saldo, int mes_1, int mes_2, int mes_3, int mes_4, int mes_5, int mes_6,
                                int mes_7, int mes_8, int mes_9, int mes_10, int mes_11, int mes_12) {

        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);
        Evaluacion_Credito_Entity evaluacion = restTemplate.getForObject("http://evaluacion-credito-service/evaluacion_credito/"+ id_solicitud_Credito, Evaluacion_Credito_Entity.class);

        boolean validador = true;
        int limite = saldo / 2; // 50% del saldo

        //Se verifica que ningun mes se retiro mas del 50% del saldo
        if (mes_1 > limite || mes_2 > limite || mes_3 > limite || mes_4 > limite ||
                mes_5 > limite || mes_6 > limite || mes_7 > limite || mes_8 > limite ||
                mes_9 > limite || mes_10 > limite || mes_11 > limite || mes_12 > limite) {

            validador = false;
        }

        if(validador){
            evaluacion.setR72(true);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return true;
        }
        else{
            evaluacion.setR72(false);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return false;
        }
    }


    public boolean validate_R73(int id_solicitud_Credito, int ingreso_mensual ,int mes_1, int mes_2, int mes_3, int mes_4, int mes_5,
                                int mes_6,int mes_7, int mes_8, int mes_9, int mes_10, int mes_11, int mes_12){

        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);
        Evaluacion_Credito_Entity evaluacion = restTemplate.getForObject("http://evaluacion-credito-service/evaluacion_credito/"+ id_solicitud_Credito, Evaluacion_Credito_Entity.class);

        double montoMinimo = (ingreso_mensual * 0.05);

        //Se valida si todos los meses se deposito a lo menos un 5% de ingresos mensuales
        if (mes_1 >= montoMinimo && mes_2 >= montoMinimo && mes_3 >= montoMinimo && mes_4 >= montoMinimo &&
                mes_5 >= montoMinimo && mes_6 >= montoMinimo && mes_7 >= montoMinimo && mes_8 >= montoMinimo &&
                mes_9 >= montoMinimo && mes_10 >= montoMinimo && mes_11 >= montoMinimo && mes_12 >= montoMinimo) {

            evaluacion.setR73(true);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return true;  // Si todos los meses cumplen con el monto mínimo, retorna true
        }
        //Se verifica si por cada trimestre, se a ingresado el 5% de ingreso mensual
        else if ((mes_1 + mes_2 + mes_3) >= montoMinimo &&
                (mes_4 + mes_5 + mes_6) >= montoMinimo &&
                (mes_7 + mes_8 + mes_9) >= montoMinimo &&
                (mes_10 + mes_11 + mes_12) >= montoMinimo) {

            evaluacion.setR73(true);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return true;
        }
        //Si no se cumple ninguna de lo anterior falla
        else {
            evaluacion.setR73(false);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return false;
        }
    }

    public boolean validate_R74(int id_solicitud_Credito, int antiguedad_cliente, int saldo_cuenta){

        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);
        Evaluacion_Credito_Entity evaluacion = restTemplate.getForObject("http://evaluacion-credito-service/evaluacion_credito/"+ id_solicitud_Credito, Evaluacion_Credito_Entity.class);

        int monto_solicitado = solicitud.getMonto_deseado();

        int porcentaje_saldo_requerido = (saldo_cuenta * 100) / monto_solicitado;
        if(antiguedad_cliente <= 2 ){
            if (porcentaje_saldo_requerido >= 20) {
                evaluacion.setR74(true);
                restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
                return true;
            }
            else{
                evaluacion.setR74(false);
                restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
                return false;
            }
        }
        else{
            if (porcentaje_saldo_requerido >= 10) {
                evaluacion.setR74(true);
                restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
                return true;
            }
            else{
                evaluacion.setR74(false);
                restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
                return false;
            }
        }
    }


    public boolean validate_R75(int id_solicitud_Credito, int saldo_cuenta, int mes_7, int mes_8, int mes_9,
                                int mes_10, int mes_11, int mes_12) {

        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);
        Evaluacion_Credito_Entity evaluacion = restTemplate.getForObject("http://evaluacion-credito-service/evaluacion_credito/"+ id_solicitud_Credito, Evaluacion_Credito_Entity.class);


        double max_retiro = saldo_cuenta * 0.3;

        if( mes_7 <= max_retiro && mes_8 <= max_retiro && mes_9 <= max_retiro &&
                mes_10 <= max_retiro && mes_11 <= max_retiro && mes_12 <= max_retiro){
            evaluacion.setR75(true);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return true;
        }
        else{
            evaluacion.setR75(false);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            return false;
        }
    }


    public String validarEvaluacionCredito(int id_solicitud_Credito){
        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);
        Evaluacion_Credito_Entity evaluacion = restTemplate.getForObject("http://evaluacion-credito-service/evaluacion_credito/"+ id_solicitud_Credito, Evaluacion_Credito_Entity.class);

        if( evaluacion.getR7().equals("moderada")){
            evaluacion.setCredito_aceptado(false);
            solicitud.setId_seguimiento_solicitud(7);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            restTemplate.put("http://solicitud-credito-service/solicitud_credito/", solicitud);

            return "Realizar revision adicional";
        }
        else if (evaluacion.getR7().equals("insuficiente")) {
            evaluacion.setCredito_aceptado(false);
            solicitud.setId_seguimiento_solicitud(7);
            restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
            restTemplate.put("http://solicitud-credito-service/solicitud_credito/", solicitud);
            return "Solicitud rechazada";

        }
        else{
            if(evaluacion.isR1() && evaluacion.isR2() && evaluacion.isR3() && evaluacion.isR4()
                    && evaluacion.isR5() && evaluacion.isR6() && evaluacion.getR7().equals("solida")) {
                evaluacion.setCredito_aceptado(true);
                solicitud.setId_seguimiento_solicitud(4);
                restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
                restTemplate.put("http://solicitud-credito-service/solicitud_credito/", solicitud);
                return "Solicitud aprobada";
            }
            else{
                evaluacion.setCredito_aceptado(false);
                solicitud.setId_seguimiento_solicitud(7);
                restTemplate.put("http://evaluacion-credito-service/evaluacion_credito/", evaluacion);
                restTemplate.put("http://solicitud-credito-service/solicitud_credito/", solicitud);
                return "Solicitud rechazada";
            }
        }

    }



    public Map<String,Object> costoTotalCredito(int id_solicitud_Credito) {
        Solicitud_Credito_Entity solicitud = restTemplate.getForObject("http://solicitud-credito-service/solicitud_credito/" + id_solicitud_Credito, Solicitud_Credito_Entity.class);

        int cuotaMensual = solicitud.getCuota_mensual();
        int anios = solicitud.getPlazo_deseado();
        int monto_deseado = solicitud.getMonto_deseado();

        int seguro_desgravamen = (int) (monto_deseado * 0.0003);
        int seguro_incendio = 20000;

        int comision_administracion= (int) (monto_deseado * 0.01);

        int costo_mensual_real = cuotaMensual + seguro_desgravamen + seguro_incendio;
        int costo_total = costo_mensual_real * anios * 12 + comision_administracion;

        Map<String,Object> respuesta = new HashMap<>();
        respuesta.put("costo_total",costo_total);
        respuesta.put("costo_mensual_real",costo_mensual_real);
        respuesta.put("comision_administracion", comision_administracion);
        respuesta.put("seguro_desgravamen",seguro_desgravamen);
        respuesta.put("seguro_incendio",seguro_incendio);


        return respuesta;
    }

}
