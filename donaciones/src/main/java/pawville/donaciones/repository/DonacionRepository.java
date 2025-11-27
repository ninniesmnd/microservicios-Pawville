package pawville.donaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawville.donaciones.model.Donacion;
import java.util.List;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {
    List<Donacion> findByEmail(String email);
    List<Donacion> findByNombre(String nombre);
}
