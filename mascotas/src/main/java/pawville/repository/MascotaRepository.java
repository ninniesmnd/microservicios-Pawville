package pawville.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawville.model.Mascota;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByTipo(String tipo);
    List<Mascota> findByLocalidad(String localidad);
    List<Mascota> findByGenero(String genero);
    List<Mascota> findByTamano(String tamano);
}