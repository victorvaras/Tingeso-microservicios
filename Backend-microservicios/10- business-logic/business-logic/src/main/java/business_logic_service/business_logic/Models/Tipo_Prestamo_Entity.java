package business_logic_service.business_logic.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tipo_Prestamo_Entity {

    private int id_Tipo_Prestamo;
    private String nombreTipo_Prestamo;
    private int plazo_maximo;
    private int tasa_anual;
    private int porcentaje_maximo_financiamiento;
}
