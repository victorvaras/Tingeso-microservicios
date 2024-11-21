package simulacion_credito.simulacion_credito_services.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import simulacion_credito.simulacion_credito_services.Entity.Simulacion_Credito_Entity;
import simulacion_credito.simulacion_credito_services.Models.Tipo_Prestamo_Entity;
import simulacion_credito.simulacion_credito_services.Repopsitory.Simulacion_Credito_Repository;

@Service
public class Simulacion_Credito_Sercive {

    @Autowired
    Simulacion_Credito_Repository simulacion_credito_repository;

    @Autowired
    RestTemplate restTemplate;

    public Simulacion_Credito_Entity newSimulacionCredito(Simulacion_Credito_Entity simulacion_credito) {
        int valor_cuota = monthly_fee_calculation(simulacion_credito);
        simulacion_credito.setValor_cuota(valor_cuota);
        return simulacion_credito_repository.save(simulacion_credito);
    }


    //Calcular cuota mensual de credito hipotecario             //tasa se ingrese en porcentaje ej: 4.5% es 4.5
    public int monthly_fee_calculation(Simulacion_Credito_Entity simulacion){

        //Tipo_Prestamo_Entity tipoPrestamo = tipo_prestamo_repository.getById(simulacion.getId_Tipo_Prestamo());

        Tipo_Prestamo_Entity tipoPrestamo =restTemplate.getForObject("http://tipo-prestamo-service/tipo_prestamo/" + simulacion.getId_Tipo_Prestamo(), Tipo_Prestamo_Entity.class);
        int monto_deseado = simulacion.getMonto_deseado();
        double tasa_anual= tipoPrestamo.getTasa_anual();
        int anios= simulacion.getPlazo_deseado();

        double tasa_mensual = tasa_anual/12/100;
        int plazo = anios * 12;

        double monto = (monto_deseado * tasa_mensual * Math.pow((1 + tasa_mensual), plazo)) /
                (Math.pow((1 + tasa_mensual), plazo) - 1);
        return (int) monto;
    }
}
