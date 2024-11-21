package tipo_prestamo.tipo_prestamo_services.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Tipo_Prestamo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tipo_Prestamo_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Tipo_Prestamo;
    private String nombreTipo_Prestamo;
    private int plazo_maximo;
    private int tasa_anual;
    private int porcentaje_maximo_financiamiento;
}
