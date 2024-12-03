package business_logic_service.business_logic.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud_Credito_Entity {

    private int id_solicitud_credito;

    private int id_cliente;
    private int id_ejecutivo;

    //Para saber que tipo prestamo se va solicitar
    private int id_Tipo_Prestamo;
    private int monto_deseado;
    private int plazo_deseado;


    private byte[] comprobante_ingresos;

    private byte[] certificado_avaluo;

    private byte[] historial_crediticio;

    private byte[] escritura_primera_vivienda;
    private byte[] estado_financiero_negocio;

    private byte[] plan_negocios;

    private byte[] presupuesto_remodelacion;

    private byte[] certificado_avaluo_actualizado;

    private int id_evaluacion_credito;
    private int id_seguimiento_solicitud;

    private Integer cuota_mensual;


}
