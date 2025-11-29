package pawville.ejercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawville.ejercicio.model.Ejercicio;

// La interfaz extiende JpaRepository, especificando la Entidad (Ejercicio)
// y el tipo de su clave primaria (Long).
@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
    // Spring Data JPA provee automáticamente: findAll(), findById(), save(), deleteById(), etc.
}