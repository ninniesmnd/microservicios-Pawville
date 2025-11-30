package pawville.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawville.model.Adopcion;

import java.util.Optional;

@Repository
public interface AdopcionRepository extends JpaRepository<Adopcion, Long> {

    Optional<Adopcion> findByIdMascota(Long idMascota);
}