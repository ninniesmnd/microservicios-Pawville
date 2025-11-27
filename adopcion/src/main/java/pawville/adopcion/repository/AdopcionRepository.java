package pawville.adopcion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawville.adopcion.model.Adopcion;

@Repository
public interface AdopcionRepository extends JpaRepository<Adopcion, Long> {
}