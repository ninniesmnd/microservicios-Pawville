package pawville.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="mascota")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String tipo;//perro o gato
    @Column(nullable = false)
    private String edad;//3 meses
    @Column(length = 250)
    private String descripcion;
    @Column(nullable = false, length = 250)
    private String localidad;
    @Column(nullable = false, length = 25)
    private String genero;
    @Column(nullable = false, length = 25)
    private String tamano;
    @Column(length = 100)
    private String raza;
    @Column(length = 100)
    private String nombreimagen;

    //Forma Multi-Part
//    @Column(length = 10)
//    private String tipoimagen;
//    @Lob
//    @Column(columnDefinition = "BYTEA")
//    private byte[] imagen;
}
