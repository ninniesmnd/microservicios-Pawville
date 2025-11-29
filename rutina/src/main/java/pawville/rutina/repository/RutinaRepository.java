package pawville.rutina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawville.rutina.model.Rutina;

import java.util.List;

@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Long> {

    List<Rutina> findByUsuarioId(String usuarioId);
}
