package pawville.ejercicio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ejercicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT") // Permite textos largos
    private String descripcion;

    // Duración en minutos
    private Integer duracionDefault;

    // Calorías quemadas por defecto por unidad de tiempo/duración
    private Integer caloriasDefault;
}